컬렉션이 비었을 때 `null`을 반환하는 경우가 있다.

```java
private final List<Cheese> cheeses;

public List<Cheese> getCheeses() {
    return cheeses.isEmpty() ? null : new ArrayList<cheesesInStock>;
}
```

만약 이 코드처럼 `null`을 반환한다면 메서드를 호출하는 곳에서 `null`에 대한 처리하는 코드를 추가로 작성해야 한다.

```java
List<Cheese> cheese = shop.getCheeses();

if(cheeses != null && cheeses.contains(Cheese.MOZZARELLA)) {
    System.out.printlnm("모차렐라 치~~즈");
}
```

컬렉션이나 배열 같은 컨테이너(container)가 비었을 때, null을 반환하는 메서드를 사용할 때면 항시 이와 같은 방어 코드를 넣어주어야 한다.<br>
만약 메서드를 호출한쪽에서 `null`에 대한 방어 코드가 없다면 오류가 발생할 수 있다. 실제로 객체가 0개일 가능성이 거의 없는 상황에서는 수년 뒤에야 오류가 발생하기도 한다.

<br/>

때로는 빈 컨테이너를 할당하는 데도 비용이 드니 null을 반환하는 쪽이 낫다는 주장도 있다.<br/>
하지만 이 주장은 두 가지 측면에서 틀린 주장이라고 할 수 있다.
1. 성능 분석 결과 이 할당이 성능 저하의 주범이라고 확인되지 않는 한(item 67), 이 정도의 성능 차이는 신경 쓸 수준이 못 된다.
2. 빈 컬렉션과 배열은 굳이 새로 할당하지 않고도 반환할 수 있다. 빈 컬렉션을 반환하는 전형적인 코드는 다음과 같다.
```java
public List<Cheese> getCheeses() {
    return new ArrayList<>(cheeses);
}
```

가능성은 적지만, 사용 패턴에 따라 빈 컬렉션 할당이 성능을 떨어뜨릴 수도 있다.<br/>
이를 해결하는 방법은 `불변(immutable) 컬렉션`을 반환하는 것이다.


### 빈 '불변' 컬렉션을 반환한다.
불변 객체는 자유롭게 공유해도 안전하다(item 17).<br/>
`Collections.emptyList` 메서드가 그러한 예다.

![image](https://user-images.githubusercontent.com/64416833/220616742-6f64f0f8-f588-48e6-aa21-8f9b517bc2a2.png)

이 역시 최적화에 해당하니 꼭 필요할 때만 사용한다. 최적화가 필요하다고 판단되면 수정 전과 후의 성능을 측정하여 실제로 성능이 개선되었는지 확인한다.

```java
public List<Cheese> getCheeses() {
    return cheeses.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeses);
}
```

컬렉션뿐만 아니라 배열을 사용할 때도 마찬가지다.<br/>

### 배열도 null을 반환하지 말고 길이가 0인 배열을 반환한다.
```java
public Cheese[] getCheeses() {
    return cheeses.toArray(new Cheese[0]);
}
```

이 방식이 성능을 떨어뜨릴 것 같다면 길이 0짜리 배열을 미리 선언해두고 매번 그 배열을 반환하면 된다.<br/>
그 이유는 길이가 0인 배열은 모두 불변이기 때문이다.

```java
private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

public Cheese[] getCheeses() {
    return cheeses.toArray(EMPTY_CHEESE_ARRAY);
}
```

단순히 성능을 개선할 목적이라면 toArray에 넘기는 배열을 할당하는 건 오히려 성능이 떨어진다는 연구 결과가 있으니 추천하지는 않는다.
```java
return Cheeses.toArray(new Cheese[cheeses.size()]);
```
