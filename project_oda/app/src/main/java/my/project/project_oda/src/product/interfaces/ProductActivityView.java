package my.project.project_oda.src.product.interfaces;

import my.project.project_oda.src.product.detail.models.Result;

public interface ProductActivityView {

   void getProductBasicSuccess(String message);
   void getProductBasicFailure(String message);

   void postBasketSuccess(String message);
   void postBasketFailure(String message);
}
