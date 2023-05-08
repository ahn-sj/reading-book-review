중첩 클래스(nested class)란 클래스 안에 정의된 클래스를 의미한다.<br/>
중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하는데 만약 그렇지 못하다면 톱레벨 클래스(Top-Level Class)로 만들어야 한다.

중첩 클래스의 종류로는 네 가지가 있다.
- 정적 중첩 클래스
- 비정적 중첩 클래스 (내부 클래스)
- 익명 내부 클래스
- 지역 내부 클래스

정적 중첩 클래스를 제외한 나머지는 내부 클래스(inner)에 해당한다.

## 정적 중첩 클래스와 비정적 중첩 클래스
정적 중첩 클래스는 클래스안에 선언되고, 바깥 클래스의 private 멤버에도 접근할 수 있다는 점만 제외하고는 일반 클래스와 똑같다.<br/>
정적 중첩 클래스는 다른 정적 멤버와 똑같은 접근 규칙을 적용받는다.<br/>
예컨대 private으로 선언하면 바깥 클래스에서만 접근할 수 있는 식이다.

```java
public class NestedClassExample {
    public static void main(String[] args) {
        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.callOuterValue();
    }
}

class Outer {
    private String ov = "Outer Class Variable";

    public static class StaticInner {
        private String siv = "Static Inner Class Variable";

        public void callOuterValue() {
            Outer outer = new Outer();
            System.out.println("call Outer private variable = " + outer.ov);
        }
    }
}
```

정적 중첩 클래스는 흔히 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.<br/>
계산기가 지원하는 연산 종류를 정의하는 열거 타입을 예로 생각해보자. (item 34)
Operation 열거 타입은 Calculator 클래스의 public static 중첩 클래스가 되어야 한다.

```java
public class CalculatorExample {
    public static void main(String[] args) {
        Calculator.Operation plus = Calculator.Operation.PLUS;
        double result = plus.apply(10, 20);

        System.out.println("result = " + result); // result = 30.0
    }
}

class Calculator {
    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;
        // public static final Operation {oper} = new Operation

        public double apply(double x, double y) {
            switch (this) {
                case PLUS:
                    return x + y;
                case MINUS:
                    return x - y;
                case TIMES:
                    return x * y;
                case DIVIDE:
                    return x / y;
            }
            throw new AssertionError("Invalid Operation = " + this);
        }
    }
}
```

그러면 Calculator를 쓰는 Client에서 `Calculator.Operation.{oper}`의 형태로 원하는 연산을 참조할 수 있다.

### 정적 중첩 클래스와 비정적 중첩 클래스의 구문상 차이는 단지 static 이다.
문법상의 차이는 static으로 나뉘지만, 의미상의 차이가 있다.

비정적 중첩 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.<br/>
그래서 비정적 중첩 클래스의 인스턴스 메서드에서 '정규화된 this'를 사용해 바깥 인스턴스의 메서드를 호출하거나 바깥 인스턴스의 참조를 가져올 수 있다.

> 정규화된 this란 `클래스명.this` 형태로 바깥 클래스의 이름을 명시하는 용법을 의미한다.

따라서 개념상 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면 **정적 중첩 클래스**로 만들어야 한다.<br/>
왜냐하면 비정적 중첩 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.

![image](https://user-images.githubusercontent.com/64416833/236755317-4bf871c6-757a-4ae6-becd-836b01b0e890.png)


비정적 중첩 클래스의 인스턴스와 바깥 인스턴스의 관계는 비정적 중첩 클래스가 인스턴스화될 때 확립되며 변경이 불가능해진다.<br/>
보통 바깥 클래스의 인스턴스 메서드에서 비정적 중첩 클래스의 생성자를 호출할 때 자동으로 만드는게 일반적이지만, 드물게는 `new Outer().new NonStaticClass()`를 호출해서 수동으로 만들기도 한다.

```java
public class NestedClassExample {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.NonStaticInner nonStaticInner1 = outer.createNonStaticInner();

        Outer.NonStaticInner nonStaticInner2 = new Outer().new NonStaticInner();
    }
}

class Outer {
    private String ov = "Outer Class Variable";

    public class NonStaticInner {...}
    
    public NonStaticInner createNonStaticInner() {
        return new NonStaticInner();
    }
}
```

예상할 수 있듯, 비정적 중첩 클래스의 인스턴스 안에 바깥 클래스의 참조가 만들어져 메모리 공간을 차지하며, 생성 시간도 더 걸린다.

### 그렇다면 비정적 중첩 클래스는 사용되지 않는가?

비정적 중첩 클래스는 어댑터를 정의할 때 자주 쓰인다.<br/>
즉, 어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 뷰로 사용하는 것이다.<br/>

예컨대 Map 인터페이스의 구현체들은 보통 (keySet, entrySet, values 메서드가 반환하는) 자신의 컬렉션 뷰를 구현할 때 비정적 멤버 클래스를 사용한다.

```java
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    ...
    
    final class KeySet extends AbstractSet<K> {...}
    final class EntrySet extends AbstractSet<Map.Entry<K,V>> {...}
    final class Values extends AbstractCollection<V> {...}
    ...
}
```

비슷하게 Set과 List 같은 다른 컬렉션 인터페이스 구현들도 자신의 반복자를 구현할 때 비정적 멤버 클래스를 주로 사용한다.
```java
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    ...
    final class ArrayListSpliterator implements Spliterator<E> {...}
    ...
}
```

### 정리
따라서 중첩 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여 정적 중첩 클래스로 만든다.<br/>
비정적 중첩 클래스로 만들면 바깥 인스턴스의 숨은 외부 참조를 갖게 되어 시간과 공간에 소모된다. 이것은 가비지 컬렉션이 바깥 클래스의 인스턴스를 수거하지 못해 메모리 누수가 생길 수 있는 심각한 문제가 존재한다. (item 7)<br/>
참조가 눈에 보이지 않으니 문제의 원인을 찾기 어려워 때때로 심각한 상황을 초래하기도 한다.

### private static 중첩 클래스는 언제 사용될까
private static 중첩 클래스는 흔히 바깥 클래스가 표현하는 객체의 한 부분을 나타낼 때 쓴다.<br/>
키와 값을 매핑시키는 Map 인스턴스를 생각해보면 많은 Map 구현체 각각의 키-값 쌍을 표현하는 엔트리(Entry) 객체들을 가지고 있다.<br/>
모든 엔트리가 맵과 연관되어 있지만 엔트리의 메서드들(getKey, getValue, setValue)은 맵을 직접 사용하지 않는다.<br/>
따라서 엔트리를 비정적 중첩 클래스로 표현하는 것은 낭비고, private static 중첩 클래스로 만드는 것이 가장 적절하다.

엔트리를 선언할 때 실수로 static을 빠뜨려도 맵은 잘 동작하겠지만, 비정적 중첩 클래스와 정적 중첩 클래스와 마찬가지로 바깥 맵으로의 참조를 갖게 되어 공간과 시간을 낭비할 것이다.<br/>
중첩 클래스가 public이냐 protected라면 하위 호환성의 이유로 static이냐 non-static이냐가 중요해진다.
> [참고: 아이템 24 멤버클래스의 하위호환성](https://github.com/Java-Bom/ReadingRecord/issues/66)

## 익명 클래스란
익명 클래스는 이름 그대로 클래스의 이름이 존재하지 않고 바깥 클래스의 멤버도 아니게 된다.<br/>
멤버(필드, 메서드)와 달리 쓰이는 시점에 선언과 동시에 인스턴스가 만들어진다. 그리고 코드의 어디서든 만들 수 있다.

또한, 오직 비정적인 문맥에서 사용될 때만 바깥 클래스의 인스턴스를 가질 수 있다.<br/>
정적 문맥에서라도 상수 변수 이외의 정적 멤버를 가질 수 없다.<br/>
> [참고: 아이템24, 익명클래스](https://github.com/Java-Bom/ReadingRecord/issues/62)<br/>
즉, 익명 클래스는 상수 표현을 위해 초기화된 final 기본 타입과 문자열 필드만 가질 수 있다.

### 사실 익명 클래스는..
익명 클래스는 응용하는데 제약이 많다.<br/>

선언한 시점에서만 인스턴스를 만들 수 있고, `instanceof` 검사나 클래스의 이름이 필요한 작업은 수행할 수 없다.<br/>
뿐만 아니라, 여러 인터페이스를 구현할 수 없고, 인터페이스를 구현하는 동시에 다른 클래스를 상속할 수도 없다.<br/>
익명 클래스를 사용하는 클라이언트는 그 익명 클래스가 상위 타입에서 상속한 멤버 외에는 호출할 수 없다.<br/>
마지막으로, 익명 클래스는 표현식 중간에 등장하므로 짧지 않으면 가독성이 떨어진다.

자바가 람다를 지원하기 전에는 작은 함수 객체나 처리 객체를 만드는 데 익명 클래스를 주로 사용했지만 이제는 람다로 대체 가능하다. (item 42)<ㅠㄱ/>
익명 클래스의 또 다른 주 쓰임은 정적 팩터리 메서드를 구현할 때이다.
```java
static List<Integer> intArrayAsList(int[] a) {
    Objects.requiredNonNull(a);
    
    return new AbstracktList<>() {
        @Override public int size() {
            return a.length;
        }
    }
}
```

## 지역 클래스는
지역 클래스는 네 가지 중첩 클래스 중 가장 드물게 사용된다.<br/>

지역 클래스는 지역변수를 선언할 수 있는 곳이면 어디서든 선언할 수 있고, 유효 범위도 지역변수와 같다.<br/>
지역 클래스는 지금까지 살펴본 중첩 클래스와 공통점이 있다.

- 중첩 클래스: 이름이 있고, 반복해서 사용할 수 있다.
- 익명 클래스: 비정적 문맥에서 사용될 때만 바깥 인스턴스에 참조할 수 있으며, 정적 멤버를 가질 수 없고, 가독성을 위해 짧게 작성해야 한다.

```java
public class NestedLocalClassExample {
    public static void main(String[] args) {
        NestedLocalClass nestedLocalClass = new NestedLocalClass();
        nestedLocalClass.createLocalClass();
    }
}

class NestedLocalClass {
    public void createLocalClass() {
        class LocalClass {
            public LocalClass() {
                System.out.println("call LocalClass Constructor");
            }
        }
        LocalClass localClass = new LocalClass();
        System.out.println("localClass.getClass() = " + localClass.getClass());
    }
}
```
```
[실행 결과]
call LocalClass Constructor
localClass.getClass() = class ej.item24.NestedLocalClass$1LocalClass
```
