### 싱글턴은

싱글턴(singleton)이란 인스턴스를 오직 하나만 생성할 수 있는 클래스이다.

전형적인 예시는 다음과 같다.
- 무상태 객체 (stateless)
- 설계상 유일해야 하는 시스템 컴포넌트

그러나 이러한 싱글턴 클래스는 이를 사용하는 클라이언트를 테스트하기 어렵다.

```java
public class Market {
    public static void main(String[] args) {
        Bottle bottle = Bottle.getInstance();
        System.out.println(bottle.fill(300));
    }
}

class Bottle {
    private static final Bottle INSTANCE = new Bottle();
    private int capacity;

    private Bottle() {
        capacity = 1000;
    }

    public static Bottle getInstance() {
        return INSTANCE;
    }

    public int fill(int capacity) {
        // 만약 물통의 용량을 바꾸고 싶다면..?
        if(this.capacity - capacity < 0) {
            throw new IllegalArgumentException("물이 넘칩니다.");
        }
        return this.capacity - capacity;
    }
}
```

싱글턴을 만드는 방식은 보통 둘 중 하나이다.

### 1. 생성자는 private으로 만들고 public static final 필드 방식으로 인스턴스에 접근하는 

```java
public class Bottle {
    public static final Bottle INSTANCE = new Bottle(); // public static final
    
    private Bottle() {...}
    ...
```

이 방식은 public static final 필드인 Bottle.INSTANCE를 초기화할 때 단 한 번 호출된다.<br/>
public이나 protected 생성자가 없으므로 전체 시스템에서 하나뿐임이 보장된다.

이것에 접근할 수 있는 예외가 있는데 그것은 권한이 있는 클라이언트가 리플렉션 API인 `AccesibleObject.setAccessible`을 사용해 private 생성자를 호출하는 것이다.<br/>
이러한 공격에 방어하려면 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지도록 하면 된다.
> 리플렉션 API란<br/>
> 컴파일된 바이트 코드를 통해 해당 클래스의 메소드, 타입, 변수까지 접근가능한 자바 API

```java
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Market {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Bottle bottle1 = Bottle.getInstance();
        Bottle bottle2 = Bottle.getInstance();

        Constructor<? extends Bottle> bottleConstructor = bottle1.getClass().getDeclaredConstructor();
        bottleConstructor.setAccessible(true);

        Bottle newBottle = bottleConstructor.newInstance();

        System.out.println("bottle2.hashCode()   = " + bottle2.hashCode());
        System.out.println("bottle1.hashCode()   = " + bottle1.hashCode());
        System.out.println("newBottle.hashCode() = " + newBottle.hashCode());
    }
}

class Bottle {
    private static final Bottle INSTANCE = new Bottle();
    private int capacity;

    private Bottle() {
        capacity = 1000;
    }

    public static Bottle getInstance() {
        return INSTANCE;
    }

    public int fill(int capacity) {
        // 만약 물통의 용량을 바꾸고 싶다면..?
        if(this.capacity - capacity < 0) {
            throw new IllegalArgumentException("물이 넘칩니다.");
        }
        return this.capacity - capacity;
    }
}
```
```
[실행결과]
bottle2.hashCode()   = 1784662007
bottle1.hashCode()   = 1784662007
newBottle.hashCode() = 1789550256
```

> [참고](https://small-stap.tistory.com/86)


**장점**<br/>
- 해당 클래스가 싱글턴임이 API에 명백히 드러난다. (public static final)
- 간결함




### 2. 정적 팩터리 메서드로 인스턴스에 접근하는 방식

```java
class Bottle {
    private static final Bottle INSTANCE = new Bottle(); // private static final

    private Bottle() { ... }

    public static Bottle getInstance() {
        return INSTANCE;
    }
    ...
```

Bottle.getInstance는 항상 같은 객체의 참조를 반환하므로 새로운 인스턴스가 생성되지 않는다.<br/>
(리플렉션을 통한 예외는 동일하게 적용된다.)


**장점**<br/>
- (마음이 바뀌면) API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
ex) 유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있다.
- 원한다면 정적 팩터리를 제네릭 싱글턴 메서드로 만들 수 있다. (item 30)
- 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있다.
ex) AS-IS: Bottle::getInstance / TO-BE: Supplier<Bottle> (item 43, 44)

이러한 장점들이 굳이 필요하지 않다면 public 필드 방식이 좋다.


### 유의점

둘 중 하나의 방식으로 만든 싱글턴 클래스를 직렬화하려면 단순히 `Serializable`을 구현한다고 선언하는 것만으로는 부족하다.

모든 인스턴스 필드를 `transient`로 선언하고 readResolve 메서드를 제공해야만(item 89) 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어지는 것을 방지할 수 있다.<br/>
만약 이렇게 하지 않으면 초기화해둔 인스턴스가 아닌 다른 인스턴스가 반환된다.
```java
// 싱글턴임을 보장해주는 readResolve 메서드
private Object readResolve() {
    return INSTANCE;
}
```

**참고**<br/>
- Thread safe
- Lazy Initialization
- Double-checked locking
- Holder

- [참고 링크](https://jeong-pro.tistory.com/86)
- [참고 링크](https://medium.com/webeveloper/%EC%8B%B1%EA%B8%80%ED%84%B4-%ED%8C%A8%ED%84%B4-singleton-pattern-db75ed29c36)
  

### 3. 싱글턴을 만드는 또 다른 방법은 원소가 하나인 열거 타입을 선언하는 방식
  
```java
enum Bottle {
    INSTANCE;
    
    public void fill() {...}
}
```

1) public 필드 방식과 비슷하지만 더 간결하고 추가 노력없이 직렬화할 수 있으며 아주 복잡한 직렬화 상황이나 리플렉션 공격에서도 제 2의 인스턴스가 생기는 일을 완벽히 막아준다.<br/>
대부분의 상황에서 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.<br/>

단, 만들려는 싱글턴이 Enum외의 클래스를 상속해야 한다면 이 방법은 사용할 수 없다.

