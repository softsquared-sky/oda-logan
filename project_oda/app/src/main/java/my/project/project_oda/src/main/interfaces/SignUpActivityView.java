package my.project.project_oda.src.main.interfaces;

public interface SignUpActivityView {

    void DuplicateSuccess(int code, String text);

    void DuplicateFailure(String message);

    void SignUpSuccess(String text);

    void SignUpFailure(String message);

}
