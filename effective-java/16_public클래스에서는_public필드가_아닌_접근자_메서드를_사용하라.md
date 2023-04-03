인스턴스 필드들을 모아놓는 일 외에는 아무 목적도 없는 클래스를 작성하려 할 때가 있다.
```java
class Point {
    public int x;
    public int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

이런 클래스는 데이터 필드에 직접 접근할 수 있으니 캡슐화의 이점을 제공하지 못한다.<br/>

```java
Point point = new Point(10, 20);
point.x = 100;
System.out.println("point = " + point.x + ", " + point.y); // point = 100, 20
```
따라서 API를 수정하지 않고는 내부 표현을 바꿀 수 없고, 불변식을 보장할 수 없으며, 외부에서 필드에 접근할 때 부수 작업을 수행할 수도 없다.

### 접근자와 변경자 메서드를 활용해 데이터를 캡슐화

public 클래스에서라면 필드들을 모두 private으로 바꾸고 public 접근자(getter)와 변경자(mutator) 메서드를 제공하도록 해야 한다.<br/>
패키지 바깥에서 접근할수 있는 클래스라면 접근자를 제공함으로써 클래스 내부 표현 방식을 언제든 바꿀 수 있는 유연성을 얻을 수 있다.

```java
class Point {
    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
```

package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 직접 노출한다 해도 아무런 문제가 없다. 그 클래스가 표현하려는 추상 개념만 올바르게 표현해주면 된다.<br/>
이 방식은 클래스 선언 면에서나 이를 사용하는 클라이언트 코드 면에서나 접근자 방식보다 깔끔하다.

```java
public class Locator {
    private static class Location {
        public double x;
        public double y;
    }

    public Location getLocation() {
        Location location = new Location();
        location.x = 10;
        location.y = 20;
        return location;
    }
}
```

클라이언트 코드가 클래스 내부 표현에 묶이기는 하나, 클라이언트도 이 클래스를 포함하는 패키지 안에서만 동작하는 코드이기 때문에 패키지 바깥 코드의 코드 변경없이 데이터 표현 방식을 바꿀 수 있다.<br/>
여기서 클라이언트 코드는 Locator를 의미한다.<br/>
private 중첩 클래스의 경우라면 수정 범위가 더 좁아져서 이 클래스를 포함하는 외부 클래스까지로 제한된다.

자바 플랫폼 라이브러리에서도 public 클래스의 필드를 직접 노출시킨 사례가 있는데 대표적인 예시가 `java.awt.package`패키지의 `Point`와 `Dimension`클래스이다.<br/>

public 클래스의 필드가 불변이라면 직접 노출할 떄의 단점이 조금 줄어들긴 해도 API를 변경하지 않고는 표현 방식을 바꿀 수 없고, 필드를 읽을 때 부수 작업을 수행한다는 단점은 여전히 존재한다.<br/>
단, 불변식은 보장할 수 있게 된다.

다음 클래스가 그 예시이다.
```
public final class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour > HOURS_PER_DAY) {
            throw new IllegalArgumentException("시간: " + hour);
        }
        if (minute < 0 || minute > MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("분: " + minute);
        }
        this.hour = hour;
        this.minute = minute;
    }
    // ...
}
```


**정리**<br/>
public 클래스는 절대 가변 필드를 직접 노출해서는 안 된다.<br/>
불변 필드라면 노출해도 덜 위험하지만 완전히 안심할 수는 없다.

하지만 상황에 따라 package-private 클래스나 private 중첩 클래스에서는 불변이든 가변이든 필드를 노출하는 편이 나을 때도 있다.

