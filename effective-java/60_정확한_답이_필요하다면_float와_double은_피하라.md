Java에는 실수형을 나타내는데 기본적으로 float과 double을 지원한다.

### float

- 4 바이트
- 정밀도 7자리
- 저장 가능 범위: 1.4x10^-45 ~ 3.4x10^38

### double

- 8바이트
- 정밀도 15 자리
- 저장 가능 범위: 4.9x10^-324 ~ 1.8x10^308

이러한 `float`과 `double`은 기본적으로 `부동 소수점 방식`을 사용한다.

<br/>

## 고정소수점과 부동소수점

컴퓨터는 0과 1로만 이루어진 2진수 체계로 이루어져있다.

> [참고: 컴퓨터는 왜 2진수를 기반으로 할까?](https://madplay.github.io/post/why-computer-is-based-on-binary-system)

따라서 실수도 2진수로 표현해야 하는데 정수에 비해 상대적으로 꽤 복잡하다.<br/>
소수점의 위치를 표현하고, 무엇이 정수 부분이고 실수 부분인지 구분해야 하기 때문이다.

이를 위해 실수를 표현하는 방식으로는 `고정 소수점 방식`과 `부동 소수점 방식`으로 나눌 수 있다.

### 고정 소수점(Fixed-Point)

고정 소수점 방식은 소수점 이상 또는 소수점 이하를 지정하여 처리하는 방식이다.<br/>
즉, 소수점의 위치를 미리 정해놓고(고정) 소수를 표현하는 방식이다.

![image](https://user-images.githubusercontent.com/64416833/224018337-18ff3977-e208-4495-9dd9-847df9fc9598.png)

위 이미지처럼 `실수를 표현하는 32비트의 고정 소수점 방식 자료형`이 있다고 가정해보자.<br/>
그리고 이 자료형은 1비트는 부호 비트로, 15비트는 정수부로, 나머지 16비트를 소수부를 사용한다고 해보겠다.<br/>
이처럼 고정 소수점 방식은 소수점의 위치를 고정시킨 후 정수부와 소수부를 표현하는 것이 특징이다.<br/>

그래서 소수점의 위치가 고정되어 있기 때문에 정수를 표현하는 것과 똑같이 실수를 표현할 수 있다.<br/>
따라서 오차없는 정확한 계산이 가능하다는 장점이 있으나 표현 범위가 제한적이라는 단점을 가졌기 때문에 잘 사용되지 않는다.

<br/>

### 부동 소수점(Floating Point)

반면에 고정 소수점 방식은 부호부(sign), 가수부(Mantissa) 그리고 지수부(Exponent)로 나눈다.

> 대부분의 부동 소수점 방식은 IEEE 754 표준을 따르고 있고 Java의 float과 double 또한 해당 스펙을 따른다.

![image](https://user-images.githubusercontent.com/64416833/224021827-0579ed55-018b-48ce-b07b-2cc0d6135ef0.png)

기본 수식은 `(-1)^S x M x 2^E`로 표현할 수 있으며 각각의 역할은 다음과 같다.

> S: 부호부(Sign) 1비트를 의미하며 0이면 양수, 1이면 음수가 된다.<br/>
> M: 가수부(Mantissa) 23비트를 의미하며 양의 정수로 표현한다.<br/>
> E: 지수부(Exponent) 8비트를 의미하며 소수점의 위치를 나타낸다.<br/>
> 본래 2는 기저를 나타내는데 컴퓨터에서 기저는 2진법이므로 부호부, 가수부, 지수부만을 표현하면 된다.

**부동소수점 변환 예시**

숫자 -314.625를 IEEE 754 부동소수점 방식으로 표현한다고 가정해보자.

1. 부호부

부호가 음수이므로 32비트의 가장 앞자리는 1이 된다.

|부호|지수|가수
|--|-----|---|
|1|...|...|

2. 가수부

숫자의 절대값을 2진수로 표현한다. 314.625를 2진수로 변환하면 다음과 같다.
```
314 = 100111010
0.625 = 0.101

314.625 = 100111010.101(2)
```
이 이진수의 소수점을 소수점 왼쪽에 1만 남도록 이동시킨다.

```
from 100111010.101(2) <br/>
to   1.00111010101(2)
=====================
result 1.00111010101 x 2^8
```

그리고 소수점을 기준으로 오른쪽 부분(00111010101)을 가수부 23비트의 앞에서부터 채우고 남는 자리는 0으로 채운다.

|부호(1비트)|지수(8비트)|가수(23비트)
|--|-----|---|
|1|...|00111010101000000000000|


3. 지수부

8에 bias(2^7=127)를 더해준 다음 2진수로 변환한다.

> bias는 2^(k-1)로 구할 수 있고 k는 지수부의 비트수

```
8 + 127 / 135

10000111(2)
```

변환한 이진수를 8비트 지수부에 채워준다.

|부호(1비트)|지수(8비트)|가수(23비트)
|--|-----|---|
|1|10000111|00111010101000000000000|

위와 같은 과정을 통해 `-314.625`를 부동소수점 방식으로 표현하면 11000011100111010101000000000000가 된다.

> 이 [링크](https://www.h-schmidt.net/FloatConverter/IEEE754.html)에서 간편하게 숫자를 부동소수점으로 변환해볼 수 있다.

이와 같은 방법은 소수점이 고정되지 않으므로 폭넓은 범위를 표현할 수 있지만, 필연적으로 오차가 발생하기 때문에 '근사치'로 표현한다고 이해해야 한다.<br/>
따라서 정확한 결과가 필요한 금융 관련 계산에서는 사용하면 안 된다.

1.03 달러에서 42센트를 사용했을 때의 계산을 수행하게 되면 테스트가 실패한다.

```java
@Test
void 부동_소수점_연산_결과() throws Exception {
    double dollar = 1.03;
    double cent = 0.42;

    double rst = dollar - cent;

    assertThat(0.61).isEqualTo(rst);
}
```
![image](https://user-images.githubusercontent.com/64416833/224031816-51cd9636-7373-4514-9455-a33f008b96bb.png)

### 올바른 방안

이러한 문제를 올바르게 해결하려면 BigDecimal이나 int, long을 사용해야 한다.

### 1) BigDecimal

소수점을 다루는 연산을 한다면 `BigDecimal` 클래스의 사용은 필수적이다.<br/>

```java
public class BigDecimal extends Number implements Comparable<BigDecimal> {
    ...
    private final BigInteger intVal;
    private final int scale; 
    private transient int precision;
    ...
}
```

- BigInteger intVal: 정수, 정수를 저장하는데 사용한다.
- int scale: 지수, 소수점 첫째 자리부터 끝나는 위치까지의 총 소수점 자리수
- int precision: 정밀도, 수가 시작하는 위치부터 끝나는 위치까지의 총 자리수

```java
@Test
void 부동_소수점_연산_결과() throws Exception {
    BigDecimal bigDecimal = BigDecimal.valueOf(1234.56789);

    System.out.println(bigDecimal.unscaledValue()); // 123456789
    System.out.println(bigDecimal.scale());         // 5
    System.out.println(bigDecimal.precision());     // 9

    assertThat(bigDecimal).isEqualTo(new BigDecimal("1234.56789"));
}
```


BigDecimal 클래스는 생성자와 파라미터로 문자열을 넘겨 생성하는 것이 일반적이지만 정적 팩토리 메서드도 제공한다.

```java
@Test
void 부동_소수점_연산_결과() throws Exception {
    BigDecimal value1 = new BigDecimal("1.03");
    BigDecimal value2 = BigDecimal.valueOf(0.42);

    BigDecimal rst = value1.subtract(value2);

    assertThat(rst).isEqualTo(new BigDecimal("0.61")); // Test passed
}
```

`BigDecimal`을 생성할 때 생성자를 사용할 때 주의점은 문자열이 아닌 double 타입을 넘기면 안 된다는 것이다.

![image](https://user-images.githubusercontent.com/64416833/224034764-93bb01a3-f56d-47a3-908b-8b743eb1ef0d.png)

하지만 BigDecimal은 기본 타입보다 쓰기 불편하고 느리다는 단점이 있다.

### 2) BigDecimal의 대안으로 정수형 int와 long을 이용

성능이 중요하고 소수점을 직접 추적할 수 있고 숫자가 크지 않다면 int나 long을 쓸 수도 있다.<br/>
이 경우에는 다룰 수 있는 값의 크기가 제한되고, 소수점을 직접 관리해야 한다.<br/>
숫자가 9자리 십진수로 표현할 수 있다면 int를 사용하고, 18자리 십진수로 표현할 수 있다면 long을 사용하고, 그 이상인 자릿수라면 BigDecimal 사용을 권장한다.



**참고**
- [링크](https://codetorial.net/articles/floating_point.html)
- [링크](https://madplay.github.io/post/the-need-for-bigdecimal-in-java)
