## 02. 의미 있는 이름
### 의도를 분명히 밝혀라
해당 챕터의 내용이 그동안 내가 제일 많이 범했던 오류 중 하나라고 생각된다.

```java
int[] arr = new int[26]
```

위 문장은 이전에 알고리즘 문제를 풀면서 내가 만든 함수명인데 26이라는 값이 어디서 나왔는 지와 배열의 쓰임이 무엇인 지를 명확히 나타내지 않았다. 

현재는 이런 문제점을 고치기 위해 최대한 네이밍에 신경을 쓰려고 하고 있다.<br/>
아래의 문장은 오늘 푼 알고리즘 문제 중 일부인데 어떤 의도인지는 나타내긴 했지만 명확한 의도는 나타내지 않았다고 생각한다.
```java
String[] suffixes = new String[word.length()];
```

현재 수준에서 의도와 용도가 담긴 보다 나은 네이밍을 한다면 `wordSuffixes` 라고 용어를 지을 것 같다.

<br/>

### 발음하기 쉬운 이름을 사용하라
> 사람은 단어에 능숙하기 때문에 발음하기 쉬운 이름은 중요하다고 한다. 심지어 발음하기 어려운 이름은 토론하는데도 어려움이 있다.

<br/>

### 검색하기 쉬운 이름을 사용하라
> 문자 하나를 사용하는 이름과 상수는 텍스트 코드에서 쉽게 눈에 띄지 않는다는 문제점이 있다.

쉽게 말하면 어떤 학과 학년의 수가 4년이라고 가정한다면 단순히 숫자 4를 하드 코딩 하는 것보다 `MAX_GRADE_PER_DEPARTMENT`를 하는 것이 좋다는 것을 의미한다.<br/>
이 파트를 읽기 전부터 하드 코딩은 좋지 않은 코딩 습관이라고 들어왔다. 그 이유는, 의미를 파악하기 어렵고 유지 보수하기 어렵다는 이유라고 알고 있었다.

최근까지의 프로젝트도 하드 코딩을 해왔지만 보다 나은 코드 습관을 위해 혼자 진행중인 프로젝트부터 상수로 처리하도록 하고 있다.
```java
...
private final int SEAT_ROW_START = 0;
private final int SEAT_COLUMN_START = 1;
...
private void createFromRowColToSeat(Airplane airplane, int seatRow, int seatCol) {
    validateSeatSize(seatRow, seatCol);

    for (int i = SEAT_ROW_START; i < seatRow; i++) {
        for (int j = SEAT_COLUMN_START; j <= seatCol; j++) {
            ...
        }
    }
}
```

<br/>

### 클래스 이름
> 클래스 이름과 객체 이름은 명사나 명사구가 적합하다.<br/>
> Customer, WikiPage, Account, AddressParser 등이 좋은 예다.<br/>
> Manager, Processor, Data, Info 등과 같은 단어는 피하고, 동사는 사용하지 않는다.

<br/>

### 메서드 이름
> 메서드 이름은 동사나 동사구가 적합하다.<br/>
> postMayment, deletePage, save 등이 좋은 예다.<br/>
> 접근자, 변경자, 조건자는 javabean 표준에 따라 값 앞에 get, set, is를 붙인다.

> 생성자를 중복정의(overload)할 때는 정적 팩토리 메서드를 사용한다.<br/>
> 메서드는 인수를 설명하는 이름을 사용한다. 예를 들어, 다음 두 예제를 살펴보자.<br/>
> 1: `Complex.FromRealNumber(23.0);`<br/>
> 2: `new Complex(23.0);`<br/>
> 1번의 예제가 2번의 예제보다 좋다.<br/>

이 말을 들으며 이전에 궁금증을 가지던 "생성자로도 동일한 기능을 하는데 왜 Builder로 해야하지"란 의문이 풀리게 되었다.<br/>

최근 정적 팩토리 메서드로 `Dto <-> Entity`간 변환 작업을 해주도록 작성하고 있다.<br/>
단순 객체 생성 작업만을 한다기 보다 중복은 줄이고 보다 객체지향다운 코드를 작성하는 것이 주된 의도라고 생각한다면 `Builder`를 사용하는 것이 보다 나은 선택지가 될 것 같다.

<br/>

### 마치면서
> 좋은 이름을 선택하는 것은 설명 능력이 뛰어나야 하고 문화적인 배경이 같아야 한다. 이것이 제일 어렵다.<br/>
> 좋은 이름을 선택하는 능력은 기술, 비즈니스, 관리 문제가 아닌 교육 문제이다.
>
> 사람들이 이름을 바꾸지 않으려는 이유 하나는 다른 개발자가 반대할까 두려워서다.<br/>
> 오히려 좋은 이름으로 바꿔주면 반갑고 고맙다.
> 
> 암기는 요즘 나오는 도구에게 맡기고 우리는 문장이나 문단처럼 읽히는 코드 아니면 적어도 표나 자료 구조처럼 읽히는 코드를 짜는 데만 집중해야 마땅하다.<br/>
> 여느 코드 개선 노력과 마찬가지로 이름 역시 나름대로 바꿨다가는 누군가 질책할지도 모른다. 그렇다고 코드를 개선하려는 노력을 중단해서는 안 된다.

<br/>

## 03. 함수
### 작게 만들어라!
> 함수를 만드는 첫째 규칙은 '작게!'다.<br/>
> 함수를 만드는 둘째 규칙은 '더 작게!'다.

> 블록과 들여쓰기<br/>
> if/else/while문 등에 들어가는 블럭은 한 줄 이어야 한다는 의미이다. 그러면 바깥을 감싸는 함수(enclosing function)가 작아질 뿐 아니라, 블록 안에서 호출하는 함수 이름을 적절히 짓는다면 코드를 이해하기도 쉬워진다.<br/>
> 이 말은 중첩 구조가 생길만큼 함수가 커져서는 안 된다는 걸 의미하고 그러므로 함수의 들여쓰기 수준은 1단이나 2단을 넘어서면 안 되는 것을 의미한다.

### 한 가지만 해라!
> 함수는 한 가지만 해야 한다. 그 한 가지를 잘 해야 한다. 그 한가지만을 해야 한다.

<br/>

[우테코 프리코스 브릿지 요구사항](https://github.com/woowacourse-precourse/java-bridge#%EC%B6%94%EA%B0%80%EB%90%9C-%EC%9A%94%EA%B5%AC-%EC%82%AC%ED%95%AD)에도 이러한 내용이 있었다.
```
추가된 요구 사항
함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
함수(또는 메서드)가 한 가지 일만 잘하도록 구현한다.
...
```
함수는 작게 만들고 한 가지의 일만 하도록 해야 하는 것이 그만큼 중요하고 어려운 일인 것이다.

<br/>

> 위에서 아래로 코드 읽기: 내려가기 규칙
> 코드는 위에서 아래로 이야기처럼 읽혀야 좋다.

### Switch문
> switch문은 작게 만들기 어렵다. case 분기가 단 두 개인 swich 문도 내 취향에는 너무 길며, 단일 블록이나 함수를 선호한다.<br/>
> 또한, '한 가지' 작업만 하는 switch문도 만들기 어렵다. 본질적으로 swtich문은 N가지를 처리한다. <br/>
> 불행하게도 switch문을 완전히 피할 방법은 없지만 각 switch문을 저차원 클래스에 숨기고 절대로 반복하지 않는 방법은 있다. 물론 다형성을 이용한다. 
> 
> ```java
> private static Money calculatePay(Employee employee) throws InvalidEmployeeType{
>     switch (employee.type) {
>         case COMMISSIONED:
>             return calculateComissionedPay(employee);
>         case HOURLY:
>             return calculateHourlyPay(employee);
>         case SALARIED:
>             return calculateSalariedPay(employee);
>         default:
>             throw new InvalidEmployeeType(employee.type);
>     }
> }
>```
> 위 함수는 몇 가지 문제가 존재한다.<br/>
> 첫째, 함수가 길다.<br/>
> 둘째, '한 가지' 작업만 수행하지 않는다. <br/>
> 셋째, SRP를 위반한다.<br/>
> 네째, OCP를 위반한다.

<br/>

### 서술적인 이름을 사용하라!
> 이름이 길어도 괜찮다. 겁먹을 필요없다. <br/>
> 길고 서술적인 이름이 짧고 어려운 이름보다 좋다.<br/>
> 길고 서술적인 이름이 길고 서술적인 주석보다 좋다.

<br/>

### 함수 인수
> 함수에서 이상적인 이수 개수는 0개(무항)이다.<br/>
> 다음은 1개(단항)고,<br/>
> 다음은 2개(이항)다.<br/>
> 3개(삼항)는 가능한 피하는 편이 좋다.

> **많이 쓰는 단항 형식**<br/>
> 함수에 인수 1개를 넘기는 이유로 가장 흔한 경우는 두 가지다.<br/>
> 하나는 인수에 질문을 던지는 경우다.<br/>
> ex. boolean firstExists("MyFile")<br/>
> 
> 다른 하나는 인수를 뭔가로 변환해 결과를 반환하는 경우다.<br/>
> ex. InputStream fileOpen("MyFile")

> **동사와 키워드**<br/>
> 함수의 의도나 인수의 순서와 의도를 제대로 표현하려면 좋은 함수 이름이 필수다.<br/>
> 단항 함수는 함수와 인수가 동사/명사 쌍을 이뤄야 한다.<br/>
> 예를 들어,write(name)은 누구나 곧바로 이해한다. 좀 더 나은 이름은 writeField(name)이다.<br/>
> 그러면 이름이 필드라는 사실이 분명히 드러난다.<br/>
> 
> 마지막 예제는 함수 이름에 키워드를 추가하는 형식이다.<br/>
> 즉, 함수 이름에 인수 이름을 넣는다.<br/>
> 예를 들어, assertEquals보다 assertExpectedEqualsActual(expected, actual)이 더 좋다. 그러면 인수 순서를 기억할 필요가 없어진다.

### 명령과 조회를 분리하라!
> 함수는 뭔가를 수행하거나 뭔가에 답하거나 둘 중 하나만 해야 한다. 둘 다 하면 안된다.<br/>
> 객체 상태를 변경하거나 아니면 객체 정보를 반환하거나 둘 중 하나다.<br/>
> ```java
> public boolean set(String attribute, String value);
> ...
> if(set("username", "unclebob"))...
> ```
> 위 코드와 같이 괴상한 코드가 나온다.
> 
> 독자 입장에서 코드를 읽어보자. 무슨 뜻일까? <br/>
> "username"이 "unclebob"으로 설정되어 있는지 확인하는 코드인가?<br/>
> 아니면 "username"을 "unclebob"으로 설정하는 코드인가?<br/>
> 
> 함수를 호출하는 코드만 봐서는 의미가 모호하다.<br/>
> 이와 같은 문제의 진짜 해결책은 명령과 조회를 분리해 혼란을 애초에 뿌리뽑는 방법이다.
> ```java
> // Best Practice
> if (attributeExists("username")) {
>     setAttribute("username", "unclebob");
>     ...
> } 
> ```

```
이항 함수, 가변 인수
```


<br/>

## 04. 주석
> 잘 달린 주석은 그 어떤 정보보다 유용하다. 경솔하고 근거 없는 주석은 코드를 이해하기 어렵게 만든다.<br/>
> 우리는 코드로 의도를 표현하지 못해, 그러니까 실패를 만회하기 위해 주석을 사용한다.<br/>
> 그러므로 주석이 필요한 상황에 처하면 곰곰이 생각하기 바란다. 상황을 역전해 코드로 의도를 표현할 방법은 없을까? 코드로 의도를 표현할 때마다 스스로를 칭찬해준다.<br/>
> 주석을 달 때마다 자신에게 표현력이 없다는 사실을 푸념해야 마땅하다.<br/>

### 주석은 나쁜 코드를 보완하지 못한다.
> 코드에 주석을 추가하는 일반적인 이유는 코드 품질이 나쁘기 때문이다.

### 코드로 의도를 표현하라.
> 확실히 코드만으로 의도를 설명하기 어려운 경우가 존재한다. 불행히고 많은 개발자가 이를 코드는 훌륭한 수단이 아니라는 의미로 해석한다.<br/>
> 다음 코드 두 예제를 살펴보자<br/>
> ```java
> 1: //직원에게 복지 혜택을 받을 자격이 있는지 검사한다.
> if((employee.flags & HOURLY_FLAG) && (employee.age > 65))
> ```
> ```java
> 2: if(employee.isEligibleForFullBenefits))`
> ```
> 몇 초만 더 생각하면 코드로 대다수 의도를 표현할 수 있다.

내 경우에도 최대한 is, can 등의 이름을 가진 메서드로 의도를 드러내는 코드를 작성하려고 노력중에 있다.
```java
isEnabledSeat(seat);
...
private static void isEnabledSeat(Seat seat) {
    if(seat.isUnableToReserve()) {
        throw new CustomCommonException(ErrorCode.RESERVED_AIRPLANE_SEAT);
    }
}
```

<br/>

**TODO 주석**
> 때로는 '앞으로 할 일'을 `//TODO` 주석으로 남겨두면 편하다.<br/>
> TODO 주석은 프로그래머가 필요하다 여기지만 당장 구현하기 어려운 업무를 기술한다.<br/>
> 불 필요 기능 삭제, 누군가에게 문제를 봐달라는 요청, 더 좋은 이름을 떠올려달라는 부탁, 앞으로 발생할 이벤트에 맞춰 코드를 고치라는 주의 등에 유용하다.<br/>
TODO 주석은 가끔 봐오긴 했지만 [내 의도에 의해 작성한 건 토이 프로젝트에서 쓴 것이 처음이다.](https://github.com/ahn-sj/airplane-reservation/blob/develop/src/main/java/airplainreservation/highestway/airplane/application/AirplaneService.java)
```java
// TODO: Converter 를 활용한 리팩토링 대상
createFromRowColToSeat(airplane, seatRow, seatCol);
```
