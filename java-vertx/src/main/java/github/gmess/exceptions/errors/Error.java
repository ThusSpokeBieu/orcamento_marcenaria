package github.gmess.exceptions.errors;

public class Error {
  private String msg;

  public Error(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
}
