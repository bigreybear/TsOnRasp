/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.edu.thu.tsfile.format;


public enum TimeSeriesChunkType implements org.apache.thrift.TEnum {
  TIME(0),
  VALUE(1);

  private final int value;

  private TimeSeriesChunkType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TimeSeriesChunkType findByValue(int value) { 
    switch (value) {
      case 0:
        return TIME;
      case 1:
        return VALUE;
      default:
        return null;
    }
  }
}
