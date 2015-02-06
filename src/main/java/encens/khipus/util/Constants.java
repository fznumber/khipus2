package encens.khipus.util;

//import encens.khipus.model.warehouse.WarehousePK;

import java.math.BigDecimal;

/**
 * This class is used to store static default values
 *
 * @author
 * @version 2.22
 */
public final class Constants {
    public static final Long defaultCompanyId = (long) 1;
    public static final String defaultCompanyName = "efestia";
    public static final String defaultCompanyNumber = "01";
    public static final String defaultCompanyNumberAttributeName = "companyNumber";
    public static final BigDecimal DAYS_BY_MONTH = new BigDecimal(30);
    public static final BigDecimal PIRIOD = new BigDecimal(45);
    public static final BigDecimal UNPOSITIVE_RETENTION = new BigDecimal(0.155);
    /* VAT (value-added tax)= IVA*/
    public static final BigDecimal VAT = new BigDecimal(0.13);
    public static final BigDecimal VAT_COMPLEMENT = new BigDecimal(0.87);
    public static final Integer MAX_INTEGER_PART = 13;
    public static final Integer MAX_FLOAT_PART = 2;
    /* Date constants */
    public static final BigDecimal MONTHS_OF_YEAR = new BigDecimal(12);
    public static final BigDecimal PREVISION_PROPORTION = new BigDecimal("0.0833");

    //currency Ids in table "moneda", change this if is required 
    public static final Long currencyIdBs = (long) 1;
    public static final Long currencyIdSus = (long) 2;

    //Schema for KHIPUS
    public static final String KHIPUS_SCHEMA = "EOS";

    public static final String FINANCES_SCHEMA = "WISE";

    //Schema for cashbox
    public static final String CASHBOX_SCHEMA = "USER01_DAF";

    //Schema for Academic
    public static final String ACADEMIC_SCHEMA = "SIS";

    public static final String SEQUENCE_TABLE_NAME = "secuencia";
    public static final String SEQUENCE_TABLE_PK_COLUMN_NAME = "tabla";
    public static final String SEQUENCE_TABLE_VALUE_COLUMN_NAME = "valor";
    public static final int SEQUENCE_ALLOCATION_SIZE = 10;
    public static final String COMPANY_FILTER_NAME = "companyFilter";
    public static final String BUSINESS_UNIT_FILTER_NAME = "businessUnitFilter";

    /* FixedAsset Constants*/
    public static final BigDecimal BASE_CURRENCY_EXCHANGE_RATE = BigDecimal.ONE;

    public static final String BANKACCOUNT_VOUCHERTYPE_FORM = "TESO_SIN_DOSIF_EMP";
    public static final String BANKACCOUNT_VOUCHERTYPE_DEBITNOTE_DOCTYPE = "ND";
    public static final String BANKACCOUNT_VOUCHERTYPE_CREDITNOTE_DOCTYPE = "NC";
    public static final String BANKACCOUNT_VOUCHERTYPE_DEPOSIT_DOCTYPE = "DEP";
    public static final String CHECK_VOUCHERTYPE_FORM = "TESO_CON_DOSIF_EMP";
    public static final String CHECK_VOUCHERTYPE_DOCTYPE = "CHQ";
    public static final String PAYROLL_DOCUMENT_SEQUENCE = "SF_TMPENC_TIP_DOC";
    public static final String PAYROLL_DOCNUMBER_PREFFIX = "PLA_";
    public static final String ADVANCEPAYMENT_DOCUMENT_SEQUENCE = "SF_TMPENC_ANPRO_TIP_DOC";
    public static final String ADVANCEPAYMENT_DOCNUMBER_PREFFIX = "ANPRO_";
    public static final String FIXEDASSET_PAYMENT_DOCUMENT_SEQUENCE = "SF_TMPENC_AF_PAY_TIP_DOC";
    public static final String FIXEDASSET_PAYMENT_DOCNUMBER_PREFFIX = "PGAF_";
    public static final String ROTATORYFUND_PAYMENT_DOCNUMBER_PREFFIX = "PGFROT_";
    public static final String ROTATORYFUND_PAYMENT_DOCUMENT_SEQUENCE = "SF_TMPENC_FROT_PAY_TIP_DOC";
    public static final String ROTATORYFUND_COLLECTION_DOCUMENT_SEQUENCE = "SF_TMPENC_FROT_COLLECTION_TIP_DOC";

    public static final String WAREHOUSE_PRODUCT_ITEM_SEQUENCE = "WAREHOUSE_PRODUCT_ITEM_SEQUENCE";
    public static final String WAREHOUSE_GROUP_SEQUENCE = "WAREHOUSE_GROUP_SEQUENCE";
    public static final String WAREHOUSE_SUBGROUP_SEQUENCE = "WAREHOUSE_SUBGROUP_SEQUENCE";
    public static final String FIXEDASSET_ITEM_SEQUENCE = "FIXEDASSET_ITEM_SEQUENCE";
    public static final String FIXEDASSET_GROUP_SEQUENCE = "FIXEDASSET_GROUP_SEQUENCE";
    public static final String FIXEDASSET_SUBGROUP_SEQUENCE = "FIXEDASSET_SUBGROUP_SEQUENCE";
    public static final String FIXEDASSET_VOUCHER_SEQUENCE = "FIXEDASSET_VOUCHER_SEQUENCE";
    public static final String FIXEDASSETMAINTENANCEREQUEST_CODE = "FIXEDASSETMAINTENANCEREQUEST_CODE";
    public static final String CHARGE_CODE_SEQUENCE = "CHARGE_CODE_SEQUENCE";
    public static final String CASHACCOUNTGROUP_CODE_SEQUENCE = "CASHACCOUNTGROUP_CODE_SEQUENCE";
    public static final String ROTATORYFUND_DOCUMENTYPE_CODE_SEQUENCE = "ROTATORYFUND_DOCUMENTYPE_CODE_SEQUENCE";
    public static final String DISCOUNTCOMMENT_CODE_SEQUENCE = "DISCOUNTCOMMENT_CODE_SEQUENCE";
    public static final String PURCHASEDOCUMENT_ADJUSTMENT_CODE_SEQUENCE = "PURCHASEDOCUMENT_ADJUSTMENT_CODE_SEQUENCE";
    public static final String VACATIONRULE_CODE_SEQUENCE = "VACATIONRULE_CODE_SEQUENCE";
    public static final String VACATIONPLAN_CODE_SEQUENCE = "VACATIONPLAN_CODE_SEQUENCE";

    public static final String DISCOUNTRULERANGE_SEQUENCE = "DISCOUNTRULERANGE_SEQUENCE";

    public static final String PURCHASEORDER_BY_BUSINESSUNIT = "PURCHASEORDER_BY_BUSINESSUNIT";

    /* Employee Constants*/
    public static final String DISMISSALCAUSE_CODE_SEQUENCE = "DISMISSALCAUSE_CODE_SEQUENCE";
    public static final String DISMISSALRULE_CODE_SEQUENCE = "DISMISSALRULE_CODE_SEQUENCE";
    public static final String DISMISSAL_CODE_SEQUENCE = "DISMISSAL_CODE_SEQUENCE";

    public static final String PAYABLEDOCUMENT_DOCUMENTNUMBER_CODE_SEQUENCE = "PAYABLEDOCUMENT_DOCUMENTNUMBER_CODE_SEQUENCE";
    public static final String PAYABLEDOCUMENT_VOUCHER_DOCUMENTNUMBER_CODE_SEQUENCE = "PAYABLEDOCUMENT_VOUCHER_DOCUMENTNUMBER_CODE_SEQUENCE";

    //system file separator like "/"
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String WAREHOUSEVOUCHER_NUMBER_PARAM = "<WAREHOUSEVOUCHER_NUMBER_PARAM>";

    // separators
    public static final String DOT_SEPARATOR = ".";
    public static final String HYPHEN_SEPARATOR = "-";
    public static final String UNDERSCORE_SEPARATOR = "_";
    public static final String BLANK_SEPARATOR = " ";

    // module constants paths
    public static final String WAREHOUSE_MODULE_PATH = "/warehouse/";
    public static final String FIXEDASSET_MODULE_PATH = "/fixedassets/";


    //Default operations
    public static final String OP_CREATE = "create";
    public static final String OP_UPDATE = "update";
    public static final String OP_DELETE = "delete";

    // Voucher form's name by module
    public static final String FIXEDASSET_VOUCHER_FORM = "AF";
    public static final String RECEIVABLES_VOUCHER_FORM = "CXC";
    public static final String HUMANRESOURCE_VOUCHER_FORM = "RRHH";
    public static final String WAREHOUSE_VOUCHER_FORM = "INV";
    public static final String IN_WAREHOUSE_MILK_COLLECTED_FORM = "INLECH";
    public static final String INPUT_PROD_WAREHOUSE = "ENTPRO";
    public static final String ORDER_VOUCHER_FORM = "PED";
    public static final String DEFAULT_VOUCHER_FORM = "INTERFAZ";
    public static final String PAYABLES_VOUCHER_FORM = "CXP";
    public static final String CASHBOX_PAYMENT_VOUCHER_FORM = "PAGO_CAJA";

    //Tributary payroll constants
    public static final Integer YEAR_DAYS = 365;
    public static final Double JUBILATION_AGE = 64.5;

    //Columns attributes
    public static final int LONG_TEXT_LENGTH = 1000;
    public static final int STRING_LENGTH_20 = 20;
    public static final int BIG_DECIMAL_DEFAULT_PRECISION = 16;
    public static final int BIG_DECIMAL_DEFAULT_SCALE = 6;

    // Resource key constants
    public static final String RK_COMMON_FROM = "Common.from";
    public static final String RK_COMMON_TO = "Common.to";
    public static final String RK_PATTERNS_DATE = "patterns.date";
    public static final String RK_PATTERNS_INTEGER_NUMBER = "patterns.integerNumber";
    public static final String VIEW_PERMISSION = "VIEW";

    //For Production Warehouse
    public static final String WAREHOUSE_PRODUCT = "PRODUCTOS LACTEOS";
    public static final String WAREHOUSE_PRODUCTION_INPUT = "INSUMOS DE PRODUCCION";

    public static final String WAREHOUSE_UHT_PRODUCT = "UHT";
    public static final String WAREHOUSE_YOGURT_PRODUCT = "YOGURT";
    public static final String WAREHOUSE_CHEESE_PRODUCT = "QUESO";

    //Employee Time Card
    public static final String EMPLOYEE_CARD_FINALIZE = "FIN DE JORNADA";

    //Indirect Cost
    public static final String INDIRECT_COST_TYPE_GENERAL = "GENERAL";
    public static final String INDIRECT_COST_TYPE_YPFB = "YPFB";
    public static final String INDIRECT_COST_TYPE_ELFEC = "ELFEC";
    public static final String INDIRECT_COST_TYPE_DEPRECIACION_HERRAMIENTAS = "DEPRECIACION HERRAMIENTAS";
    public static final String INDIRECT_COST_TYPE_MANO_OBRA_PRODUCCION = "MANO OBRA PRODUCCION";
    public static final String INDIRECT_COST_TYPE_MANO_OBRA_PRODUCCION_EVENTUAL = "MANO OBRA PRODUCCION EVENTUAL";

    //Production diari
    public static  final String ESTATE_ARTICLE_COMPOSITE = "COMPUESTO";
    public static  final String ESTATE_ARTICLE_PARAMETRIZE = "PARAMETRIZABLE";
    public static  final String ESTATE_ARTICLE_NOTVERIFY = "NOVERIFICABLE";
    public static final String ESTATE_COSTCONFIG = "MANOOBRA";
    //id Milk
    public static final String ID_ART_RAW_MILK = "26";
    //public static final WarehousePK COD_WAREHUOSE_MILK_COLLECTED = new WarehousePK("01","5");

    public static final String COD_ART_WATER = "93";
    public static final String ID_ART_GROUP_CHEESE = "9";
    public static final String ID_ART_GROUP_UHT = "7";
    public static final String ID_ART_GROUP_YOGURT = "8";
    //DELIVERED
    public static final String ESTATE_ORDER_DELIVERED_INCASH = "DELIVERED";
    public static final String ESTATE_ORDER_DELIVERED = "ECH";
    public static final String COD_CUT_CHEESE = "147";
    public static final String COD_CHEESE_EDAM = "134";
    public static final String COD_CHEESE_PRESSED = "114";
    public static final String BUSINESS_UNIT_COD_DEFAULT = "01";
    public static final String COD_COMPANY_DEFAULT = "01";

    //PRODUCTION
    public static final Double PRICE_UNIT_MILK = 3.0;
    public static final String FINACESS_USER_UNIT_DEFAULT = "ADM";
    public static final String DEFAULT_COST_CENTER_PRODUCTION = "0111";
    public static final String CODE_WAREHOUSE_PRODUCT_END = "2";
    public static final String CODE_WAREHOUSEDOCUMENTYPE_EGRESS = "2";
    public static final String CODE_WAREHOUSEDOCUMENTYPE_RECEPTION = "1";
    public static final String CODE_WAREHOUSE_PRODUCT_MATERIAL = "3";
    public static final String CODE_WAREHOUSE_PRODUCT_INPUT = "1";
    public static final String DEFAULT_CODE_WAREHOUSE_COLLECTION_MILK = "5";
    //condicion purchase order
    public static final String WITH_BILL = "CONFACTURA";
    public static final String WITHOUT_BILL = "SINFACTURA";
    //
    public static final String STATE_ITEM_PRODUCT_NOPRODUCER = "NOPRODUCIDO";


    private Constants() {
    }
}
