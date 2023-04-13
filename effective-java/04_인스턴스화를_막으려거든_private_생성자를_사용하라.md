### 인스턴스화를 막는다??

인스턴스화를 막는다는 것은 인스턴스를 생성하지 못하게 한다는 것과 마찬가지이다.

그렇다면 어떤 상황에 인스턴스를 생성 못하게 해야할까

### 1. 기본 타입 값이나 배열 관련 메서드들을 모아 놓은 클래스

`java.lang.Math`와 `java.util.Arrays` 클래스가 대표적인 예시이다.<br/>
두 클래스의 공통된 특징은 어디서든 사용할 수 있고, 재사용이 가능하다는 점이다.

> 두 패키지는 클래스 로더가 클래스 파일(xxx.class)을 로딩하는 과정 중 로드(Loading) 시점에 읽어와서 어디서나 사용할 수 있던 것이다.

### 2. 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드(혹은 팩터리)를 모아 놓은 클래스

`java.util.Collections`이 대표적인 예시이다.<br/>
`java.util.Arrays`와 마찬가지로 정적 메서드와 정적 필드로만 이루어져 있다.

```java
public class Collections {
    
    private Collections() {
    }
    
    private static final int BINARYSEARCH_THRESHOLD   = 5000;
    private static final int REVERSE_THRESHOLD        =   18;
    private static final int SHUFFLE_THRESHOLD        =    5;
    ...
    
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
    ...
```

### 3. final 클래스와 관련한 메서드들을 모아놓은 클래스

final 클래스를 상속해서 하위 클래스에 메서드를 넣는 





