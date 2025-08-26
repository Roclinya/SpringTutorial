package enumUtil;

public enum CardType {
    
    CREDIT, DEBIT;
    
    public static CardType getEnum(String value) {
        if (value == null || value.length() < 1) {
            return null;
        }
        
        //此處CardType忽略打小寫去比對value回傳CardType對應結果
        for (CardType t : CardType.values()) {
        	System.out.println(values());
            if (t.name().equalsIgnoreCase(value)) {
                return t;
            }
        }
        
        return null;
    }
}  