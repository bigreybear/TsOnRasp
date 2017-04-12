/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.edu.thu.tsfile.format;


/**
 * Common types used by frameworks(e.g. hive, pig) using TSFile.  This helps map
 * between types in those frameworks to the base types in TSFile.  This is only
 * metadata and not needed to read or write the data.
 * 
 * hold this place for future extension
 */
public enum ConvertedType implements org.apache.thrift.TEnum {
  /**
   * a BYTE_ARRAY actually contains UTF8 encoded chars
   */
  UTF8(0);

  private final int value;

  private ConvertedType(int value) {
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
  public static ConvertedType findByValue(int value) { 
    switch (value) {
      case 0:
        return UTF8;
      default:
        return null;
    }
  }
}
