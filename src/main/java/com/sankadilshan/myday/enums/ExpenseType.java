package com.sankadilshan.myday.enums;

import lombok.Getter;
import lombok.Setter;

public enum ExpenseType {
        FOOD("FOOD"),
        TRANSPORT("TRANSPORT"),
        CLOTHES("CLOTHES"),
        ACCOMMODATION("ACCOMADATION"),
        OTHER("OTHER");

        private String type;

        ExpenseType(String type) {
                this.type= type;
        }

        public String getType(){
                return this.type;
        }
}
