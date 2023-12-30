package com.fileattente.Enums;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum Status {
    
    ENATTENTE ("enattente"),
    TERMINER("terminer"),
    ENCOURS("encours");
    private final String displayName;

 

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
