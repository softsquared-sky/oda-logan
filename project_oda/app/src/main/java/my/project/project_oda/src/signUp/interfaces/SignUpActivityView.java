package my.project.project_oda.src.signUp.interfaces;

public interface SignUpActivityView {

    void DuplicateSuccess(int code, String text);

    void DuplicateFailure(String message);

    void SignUpSuccess(int code, String text);

    void SignUpFailure(String message);

}
