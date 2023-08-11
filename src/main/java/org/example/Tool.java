package org.example;

import java.math.BigDecimal;

public enum Tool {
    CHNS(ToolType.CHAINSAW, ToolBrand.STIHL),
    LADW(ToolType.LADDER, ToolBrand.WERNER),
    JAKD(ToolType.JACKHAMMER, ToolBrand.DEWALT),
    JAKR(ToolType.JACKHAMMER, ToolBrand.RIDGID);

    private final ToolType toolType;
    private final ToolBrand toolBrand;

    Tool(ToolType toolType, ToolBrand toolBrand) {
        this.toolType = toolType;
        this.toolBrand = toolBrand;
    }

    ToolType getToolType() {
        return toolType;
    }

    ToolBrand getToolBrand() {
        return toolBrand;
    }

    enum ToolBrand {
        DEWALT("DeWalt"), RIDGID("Ridgid"), STIHL("Stihl"), WERNER("Werner");

        private final String text;

        ToolBrand(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    enum ToolType {
        LADDER("Ladder", BigDecimal.valueOf(1.99)),
        CHAINSAW("Chainsaw", BigDecimal.valueOf(1.49)),
        JACKHAMMER("Jackhammer", BigDecimal.valueOf(2.99));

        private final String text;
        private final BigDecimal dailyCharge;

        ToolType(String text, BigDecimal dailyCharge) {
            this.text = text;
            this.dailyCharge = dailyCharge;
        }

        public String getText() {
            return text;
        }

        public BigDecimal getDailyCharge() {
            return dailyCharge;
        }
    }
}
