package data;

public class RequestDTO {
  private int delay;

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  @Override
  public String toString() {
    return "RequestDTO{" +
        "delay=" + delay +
        '}';
  }
}
