### [전체 예시 코드](https://github.com/ahn-sj/reading-book-review/tree/master/effective-java/example/src/ej/item04)

---

### 인스턴스화를 막는다??

인스턴스화를 막는다는 것은 인스턴스를 생성하지 못하게 한다는 것과 마찬가지이다.

그렇다면 어떤 상황에 인스턴스를 생성 못하게 해야할까<br/>
기본적으로 정적 메서드와 정적 필드만을 담은 클래스를 만들고 싶을 때 유용하다.


### 1. 기본 타입 값이나 배열 관련 메서드들을 모아 놓은 클래스

ex) `java.lang.Math`, `java.util.Arrays`, ...<br/>

```java
public class Arrays {
    private Arrays() {}
    
    public static void sort(int[] a) {...}
    public static boolean equals(int[] a, int[] a2) {...}
    public static <T> List<T> asList(T... a) {...}
    ...
```

두 클래스의 공통된 특징은 어디서든 사용할 수 있고, 재사용이 가능하다는 점이다.

> 두 패키지는 클래스 로더가 클래스 파일(xxx.class)을 로딩하는 과정 중 로드(Loading) 시점에 읽어와서 어디서나 사용할 수 있던 것이다.


### 2. 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드(혹은 팩터리)를 모아 놓은 클래스

ex) `java.util.Collections`이 대표적인 예시이다.<br/>
`java.util.Arrays`와 마찬가지로 private 생성자가 선언되고 정적 메서드와 정적 필드로만 이루어져 있다.

```java
public class Collections {
    
    private Collections() {}
    
    private static final int BINARYSEARCH_THRESHOLD   = 5000;
    private static final int REVERSE_THRESHOLD        =   18;
    private static final int SHUFFLE_THRESHOLD        =    5;
    ...
    
    public static <T> void sort(List<T> list, Comparator<? super T> c) {...}
    ...
```

### 3. final 클래스와 관련한 메서드들을 모아놓은 클래스

> p26 line 8: final 클래스를 상속해서 하위 클래스에 메서드를 넣는 건 불가능하기 때문이다.<br/>
>
> 처음엔 이 말과 언제 유틸리티 클래스를 써야하는지의 관계를 알지 못했는데 의미하는 바는 다음과 같았다.

final class는 아래 이미지처럼 상속을 하지 못하게 설계되어있다.<br/>

![image](https://user-images.githubusercontent.com/64416833/232414505-7ff23f80-b884-4283-90e8-213f9c628a84.png)

그런데 해당 클래스를 사용하는 입장이라면 직접 메서드를 추가할 수 없게 된다.<br/>
따라서 별도의 유틸리티 클래스로 만들어 정적 메서드로 만드는게 방법이라는 것을 말하는거였다.

```java
public class FinalClassExample {
    public static void main(String[] args) {
        FinalClass finalClass = new FinalClass();

        FinalClassUtils.printHashCodeByFinalClass(finalClass); // >>>> 997110508
        FinalClassUtils.printToStringByFinalClass(finalClass); // >>>> ej.item04.FinalClass@3b6eb2ec
    }
}

class FinalClassUtils {
    public static void printHashCodeByFinalClass(FinalClass finalClass) {
        System.out.println(">>>> " + finalClass.hashCode());
    }

    public static void printToStringByFinalClass(FinalClass finalClass) {
        System.out.println(">>>> " + finalClass.toString());
    }
}

final class FinalClass {
    void printClass() {
        System.out.println(">> " + this.getClass());
    }
}
```

이 예시로는 크게 와닿지 않을 수도 있는데, Java의 대표적인 `final class`인 `String.java`가 존재한다.<br/>
이 클래스(final 클래스)와 관련된 메서드들을 모아둔 `org.junit.platform.commons.util.StringUtils`가 이러한 형태로 구성되어 있다.
```java
@API(status = INTERNAL, since = "1.0")
public final class StringUtils {
    private StringUtils() {}
    
    public static boolean isBlank(String str) {...}
    public static boolean isNotBlank(String str) {...}
    ...
}
```

---

전역으로 사용할 수 있는 정적 필드와 정적 메서드만 담은 클래스는 보통 유틸리티 클래스로 사용하기 위해 생성된다.<br/>
그러나 유틸리티 클래스는 인스턴스로 만들어 쓰려고 설계한 것이 아니다.

## 이렇게 하면 인스턴스 생성을 막을 수 있지 않을까?

### 생성자를 명시하지 않는다면?

생성자를 명시하지 않으면 컴파일러가 자동으로 기본 생성자(public)를 만들어준다.<br/>
즉, 클라이언트는 생성자가 자동으로 생성된 것인지 의도된 것인지 구분할 수 없다.

### 추상 클래스로 만든다면?

추상 클래스는 기본적으로 인스턴스로 생성하는 것이 불가능하다. <br/>
주의할 점이 이 말은 상속하라는 의미가 아니다.


### 정리하면 인스턴스화를 막는 방법은 private 생성자를 추가하는 것이다.

생성자가 private이기 때문에 클래스 바깥에서는 접근할 수 없고, 클래스의 인스턴스화를 막을 수 있다.<br/>

```java
class AnimalCryingUtils {
    private AnimalCryingUtils() {
        throw new AssertionError();
    }

    public static void foxCrying() {
        System.out.println("여우~");
    }

    public static void lionCrying() {
        System.out.println("어흥~");
    }
}
```

꼭 예외를 던질 필요는 없지만 클래스 안에서 실수로라도 생성자를 호출하지 못하도록 해준다.

![image](https://user-images.githubusercontent.com/64416833/232413473-272298f8-eb53-4969-be56-3f0b16b9da04.png)



## 근본적으로 생성자를 왜 private 로 만들까?

불필요한 객체의 생성을 막아주기 위함이다.<br/>
앞서 살펴본 `AnimalCryingUtils`, `java.lang.Math`, `java.util.Arrays`의 클래스는 해당 클래스의 인스턴스 생성없이도 클래스에 정의된 메서드를 사용 가능하다.<br/>
따라서, 해당 클래스의 인스턴스 생성이 무의미하다.

- [언제 static 함수 모음 Class를 만들어야 할까?](http://kwon37xi.egloos.com/4844149)
- [final 클래스 질문(이펙티브 자바 item 4 관련)](https://okky.kr/questions/722887)
- [참고](https://github.com/woowacourse-study/2022-effective-java/blob/main/02%EC%9E%A5/%EC%95%84%EC%9D%B4%ED%85%9C_04/%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4%ED%99%94%EB%A5%BC%20%EB%A7%89%EA%B8%B0%20%EC%9C%84%ED%95%B4%20private%20%EC%83%9D%EC%84%B1%EC%9E%90%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0.md)
