package cn.edu.thu.tsfile.timeseries.read.qp;

import java.util.HashMap;
import java.util.Map;

/**
 * this class contains several constants used in SQL.
 * 
 * @author kangrong
 *
 */
public class SQLConstant {
    public static final String MREGE_EXTENSION = "merge";
    public static final String ERR_EXTENSION = "err";
    public static final String PATH_SEPARATOR = ".";
    public static final String PATH_SEPARATER_NO_REGEX = "\\.";

    public static final String DEFAULT_DELTA_OBJECT_TYPE = "defalut_delta_object_type";

    public static final String RESERVED_TIME = "time";
    public static final String RESERVED_FREQ = "freq";
    public static final String RESERVED_DELTA_OBJECT = "delta_object";
    public static final String IS_AGGREGATION = "IS_AGGREGATION";

    public static final String lineFeedSignal = "\n";
    public static final String ROOT = "root";
    public static final String METADATA_PARAM_EQUAL = "=";

    public static final int KW_AND = 1;
    public static final int KW_OR = 2;
    public static final int KW_NOT = 3;

    public static final int EQUAL = 11;
    public static final int NOTEQUAL = 12;
    public static final int LESSTHANOREQUALTO = 13;
    public static final int LESSTHAN = 14;
    public static final int GREATERTHANOREQUALTO = 15;
    public static final int GREATERTHAN = 16;
    public static final int EQUAL_NS = 17;

    public static final int TOK_SELECT = 21;
    public static final int TOK_FROM = 22;
    public static final int TOK_WHERE = 23;
    public static final int TOK_INSERT = 24;
    public static final int TOK_DELETE = 25;
    public static final int TOK_UPDATE = 26;
    public static final int TOK_QUERY = 27;
    // public static final int TOK_VIRTUAL_TABLE = 30;
    // public static final int TOK_TABNAME = 31;
    // public static final int TOK_TABREF = 32;

    public static final int TOK_AUTHOR_CREATE = 41;
    public static final int TOK_AUTHOR_DROP = 42;
    public static final int TOK_AUTHOR_GRANT = 43;
    public static final int TOK_AUTHOR_REVOKE = 44;

    public static final int TOK_DATALOAD = 45;

    public static final int TOK_METADATA_CREATE = 51;
    public static final int TOK_METADATA_DELETE = 52;
    public static final int TOK_METADATA_SET_FILE_LEVEL = 53;
    public static final int TOK_PORPERTY_CREATE = 54;
    public static final int TOK_PORPERTY_ADD_LABEL = 55;
    public static final int TOK_PORPERTY_DELETE_LABEL = 56;
    public static final int TOK_PORPERTY_LINK = 57;
    public static final int TOK_PORPERTY_UNLINK = 58;



    public static Map<Integer, String> tokenSymbol = new HashMap<Integer, String>();
    public static Map<Integer, String> tokenNames = new HashMap<Integer, String>();
    public static Map<Integer, Integer> reverseWords = new HashMap<Integer, Integer>();

    static {
        tokenSymbol.put(KW_AND, "&");
        tokenSymbol.put(KW_OR, "|");
        tokenSymbol.put(KW_NOT, "!");
        tokenSymbol.put(EQUAL, "=");
        tokenSymbol.put(NOTEQUAL, "<>");
        tokenSymbol.put(EQUAL_NS, "<=>");
        tokenSymbol.put(LESSTHANOREQUALTO, "<=");
        tokenSymbol.put(LESSTHAN, "<");
        tokenSymbol.put(GREATERTHANOREQUALTO, ">=");
        tokenSymbol.put(GREATERTHAN, ">");
    }

    static {
        tokenNames.put(KW_AND, "and");
        tokenNames.put(KW_OR, "or");
        tokenNames.put(KW_NOT, "not");
        tokenNames.put(EQUAL, "equal");
        tokenNames.put(NOTEQUAL, "not_equal");
        tokenNames.put(EQUAL_NS, "equal_ns");
        tokenNames.put(LESSTHANOREQUALTO, "lessthan_or_equalto");
        tokenNames.put(LESSTHAN, "lessthan");
        tokenNames.put(GREATERTHANOREQUALTO, "greaterthan_or_equalto");
        tokenNames.put(GREATERTHAN, "greaterthan");

        tokenNames.put(TOK_SELECT, "TOK_SELECT");
        tokenNames.put(TOK_FROM, "TOK_FROM");
        tokenNames.put(TOK_WHERE, "TOK_WHERE");
        tokenNames.put(TOK_INSERT, "TOK_INSERT");
        tokenNames.put(TOK_DELETE, "TOK_DELETE");
        tokenNames.put(TOK_UPDATE, "TOK_UPDATE");
        tokenNames.put(TOK_QUERY, "TOK_QUERY");

        tokenNames.put(TOK_AUTHOR_CREATE, "TOK_AUTHOR_CREATE");
        tokenNames.put(TOK_AUTHOR_DROP, "TOK_AUTHOR_DROP");
        tokenNames.put(TOK_AUTHOR_GRANT, "TOK_AUTHOR_GRANT");
        tokenNames.put(TOK_AUTHOR_REVOKE, "TOK_AUTHOR_REVOKE");
        tokenNames.put(TOK_DATALOAD, "TOK_DATALOAD");

        tokenNames.put(TOK_METADATA_CREATE, "TOK_METADATA_CREATE");
        tokenNames.put(TOK_METADATA_DELETE, "TOK_METADATA_DELETE");
        tokenNames.put(TOK_METADATA_SET_FILE_LEVEL, "TOK_METADATA_SET_FILE_LEVEL");
        tokenNames.put(TOK_PORPERTY_CREATE, "TOK_PORPERTY_CREATE");
        tokenNames.put(TOK_PORPERTY_ADD_LABEL, "TOK_PORPERTY_ADD_LABEL");
        tokenNames.put(TOK_PORPERTY_DELETE_LABEL, "TOK_PORPERTY_DELETE_LABEL");
        tokenNames.put(TOK_PORPERTY_LINK, "TOK_PORPERTY_LINK");
        tokenNames.put(TOK_PORPERTY_UNLINK, "TOK_PORPERTY_UNLINK");

    }

    static {
        reverseWords.put(KW_AND, KW_OR);
        reverseWords.put(KW_OR, KW_AND);
        reverseWords.put(EQUAL, NOTEQUAL);
        reverseWords.put(NOTEQUAL, EQUAL);
        reverseWords.put(LESSTHAN, GREATERTHANOREQUALTO);
        reverseWords.put(GREATERTHANOREQUALTO, LESSTHAN);
        reverseWords.put(LESSTHANOREQUALTO, GREATERTHAN);
        reverseWords.put(GREATERTHAN, LESSTHANOREQUALTO);
    }

    public static boolean isReservedPath(Path pathStr) {
        return pathStr.equals(SQLConstant.RESERVED_TIME)
                || pathStr.equals(SQLConstant.RESERVED_FREQ)
                || pathStr.equals(SQLConstant.RESERVED_DELTA_OBJECT);

    }
}
