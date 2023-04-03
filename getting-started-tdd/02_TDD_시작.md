## 02. TDD 시작

TDD는 Test-driven Development의 약자로 테스트 주도 개발을 의미한다.

TDD는 테스트부터 시작한다.<br/>
구체적으로는 구현을 먼저 하고 나중에 테스트하는 것이 아닌 테스트를 하고 그다음에 구현한다.

구현 코드가 없는데 어떻게 테스트할 수 있을까?<br/>
여기서 테스트를 먼저 한다는 것은 기능이 올바르게 동작하는지 검증하는 테스트 코드를 작성한다는 것을 의미한다.<br/>
즉 기능을 검증하는 테스트 코드를 먼저 작성하고 테스트를 통과시키기 위해 개발을 진행한다.

이해를 돕기 위해 간단한 덧셈 기능을 TDD로 구현해보자.

> `@Test`: 해당 애너테이션이 붙은 메서드를 테스트 메서드로 인식하게 된다.<br/>
> `Assertions.assertEquals`: 인자로 받은 두 값이 동일한지 비교한다. 첫 번쨰 인자는 기대한 값이고, 두 번째 인자는 실제 값이다.

```java
package test.tdd.chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void plus() throws Exception {
        int result = Calculator.plus(1, 2);
        Assertions.assertEquals(3, result);
    }
}
```

그러나 위 코드는 Calculator 클래스가 존재하지 않아 컴파일 에러가 발생하게 된다.<br/>
덧셈 기능을 테스트하는 코드를 작성하면서 클래스, 메서드, 반환 타입 등을 고려해서 해당 클래스를 작성하도록 한다.

우선 테스트를 짠 코드가 성공하려면 Calculator.plus의 반환 값이 3이어야 한다. 그래서 일단은 다음과 같이 구현하도록 한다.
```java
package test.tdd.chap02;

public class Calculator {

    public static int plus(int i, int i1) {
        return 3;
    }
}
```
중요한 것은 이런 단계를 차근차근 해나가야 한다는 것이다.

하나의 검증 단계가 추가된다면??<br/>
두 개의 검증을 통과하도록 코드를 변경해야 한다.

```java
package test.tdd.chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void plus() throws Exception {
        assertEquals(3, Calculator.plus(1, 2));
        assertEquals(5, Calculator.plus(4, 1));
    }
}
```
```java
public static int plus(int i1, int i2) {
    return i1 + i2;
}
```

> NOTE<br/>
> src/test/java 폴더는 배포 대상이 아니므로 해당 폴더에 완성되지 않은 코드가 있어도 배포되는 것을 방지할 수 있다.

### 그래서 TDD는

이런 식으로 TDD는 테스트를 먼저 작성하고 테스트에 실패하면 테스트를 통과시킬 만큼 코드를 추가하는 과정을 반복하면서 점직적으로 기능을 완성해 나간다.<br/>
테스트를 통과한 뒤에는 개선할 코드가 있으면 리팩터링을 하는 식으로 기능을 완성해 나간다.

> 레드-그린-리팩터<br/>
> TDD 사이클을 레드-그린-리팩터로 부르기도 한다.<br/>
> 레드는 실패하는 테스트를 의미하고, 그린은 성공한 테스트를 의미하고 리팩터는 이름 그대로 리팩터링 과정을 의미한다.

## 테스트가 개발을 주도

테스트 코드를 먼저 작성하면 테스트가 개발을 주도하게 된다.<br/>
테스트 코드를 만들면 다음 개발 범위가 정해진다. <br/>
테스트 코드가 추가되면서 검증하는 범위가 넓어질수록 구현도 점점 완성되어가고 이렇게 테스타가 개발을 주도해 나간다.

**지속적인 코드 정리**

구현을 완료한 뒤에는 리팩터링을 진행하게 되는데 리팩터링할 대상이 눈에 들어오면 리팩터링으로 코드를 정리한다.<br/>
TDD는 개발 과정에서 지속적으로 코드 정리를 하므로 코드 품질이 급격히 나빠지지 않게 막아주는 효과가 있다. 이는 향후 유지보수 비용을 낮추는데 기여한다.

**빠른 피드백**

TDD가 주는 이점은 코드 수정에 대한 피드백이 빠르다는 점이다.<br/>
새로운 코드를 추가하거나 기존 코드를 수정하면 테스트를 돌려서 해당 코드가 올바른지 바로 확인할 수 있다. 이는 잘못된 코드가 배포되는 것을 방지한다.

