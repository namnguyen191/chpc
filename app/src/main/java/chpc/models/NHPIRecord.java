package chpc.models;

public class NHPIRecord {
  private String refDate;
  private String geo;
  private double value;

  public NHPIRecord(String geo, String refDate, double value) {
    this.refDate = refDate;
    this.geo = geo;
    this.value = value;
  }

  public String getRefDate() {
    return this.refDate;
  }

  public String getGeo() {
    return this.geo;
  }

  public double getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "NHPIRecord{" +
        "geo='" + this.geo + "'" +
        ", refDate=" + "'" + this.refDate + "'" +
        ", value=" + this.value +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    // self check
    if (this == o)
      return true;
    // null check
    if (o == null)
      return false;
    // type check and cast
    if (getClass() != o.getClass())
      return false;

    return this.toString().equals(o.toString());
  }

  public static void main(String[] args) {
    var nhpiRecord = new NHPIRecord("Canada", "1998-01", 29.8);
    System.out.println(nhpiRecord);
  }
}
