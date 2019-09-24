package my.project.project_oda.src.search.interfaces;

import java.util.List;

import my.project.project_oda.src.search.models.PopularItem;

public interface SearchActivityView {

   void getPopularSuccess(List<PopularItem> list);
   void getPopularFailure(String message);

}
