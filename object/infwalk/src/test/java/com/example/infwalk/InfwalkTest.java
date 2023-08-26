package com.example.infwalk;

import com.example.infwalk.coupon.AmountDiscountCoupon;
import com.example.infwalk.coupon.NoneDiscountCoupon;
import com.example.infwalk.coupon.PercentDiscountCoupon;
import com.example.infwalk.purchase.Purchase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InfwalkTest {

    @Test
    void 강의를_구매한다_일치하지_않는_쿠폰() {
        // given
        Customer customer = new Customer(1, "jay", new Money(100_000), new PercentDiscountCoupon(10));
        Walker walker = new Walker(111, "papi");
        Lecture lecture = new Lecture("오브젝트의 사실과 오해", walker.getWalkerId(), RetentionType.INFINITE, new Money(50_000));

        // when
        Purchase purchase = lecture.buy(customer, new PercentDiscountCoupon(50));

        // then
        assertThat(purchase.getPrice()).isEqualTo(50_000);
        assertThat(customer.getMoney().getValue()).isEqualTo(50_000);
        assertThat(customer.getCouponSize()).isEqualTo(1);
    }

    @Test
    void 강의를_구매한다_쿠폰사용X() {
        // given
        Customer customer = new Customer(1, "jay", new Money(100_000), new PercentDiscountCoupon(10));
        Walker walker = new Walker(111, "papi");
        Lecture lecture = new Lecture("오브젝트의 사실과 오해", walker.getWalkerId(), RetentionType.INFINITE, new Money(50_000));

        // when
        Purchase purchase = lecture.buy(customer, new NoneDiscountCoupon());

        // then
        assertThat(purchase.getPrice()).isEqualTo(50_000);
        assertThat(customer.getMoney().getValue()).isEqualTo(50_000);
        assertThat(customer.getCouponSize()).isEqualTo(1);
    }



    @Test
    void 강의를_구매한다_금액할인쿠폰() {
        // given
        Customer customer = new Customer(1, "jay", new Money(100_000), new AmountDiscountCoupon(20_000));
        Walker walker = new Walker(111, "papi");
        Lecture lecture = new Lecture("오브젝트의 사실과 오해", walker.getWalkerId(), RetentionType.INFINITE, new Money(50_000));

        // when
        Purchase purchase = lecture.buy(customer, new AmountDiscountCoupon(20_000));

        // then
        assertThat(purchase.getPrice()).isEqualTo(30_000);
        assertThat(customer.getMoney().getValue()).isEqualTo(100000 - 30000);
        assertThat(customer.getCouponSize()).isEqualTo(0);
    }

    @Test
    void 강의를_구매한다_퍼센트할인쿠폰() {
        // given
        Customer customer = new Customer(1, "jay", new Money(100_000), new PercentDiscountCoupon(10));
        Walker walker = new Walker(111, "papi");
        Lecture lecture = new Lecture("오브젝트의 사실과 오해", walker.getWalkerId(), RetentionType.INFINITE, new Money(50_000));
        // when
        Purchase purchase = lecture.buy(customer, new PercentDiscountCoupon(10));

        // then
        assertThat(purchase.getPrice()).isEqualTo(45_000);
        assertThat(customer.getMoney().getValue()).isEqualTo(100000 - 45000);
        assertThat(customer.getCouponSize()).isEqualTo(0);
    }

}
