package my.project.project_oda.src.cart.interfaces;

import java.util.List;

import my.project.project_oda.src.cart.models.CartItem;

public interface CartActivityView {

    void getBasketSuccess(List<CartItem> basketList, int code);

    void getBasketFailure(String message);

}
