package br.com.fiap.gerenciadorDeReservas.entities.enuns;

public enum statusEnum {
    ABERTA,
    CANCELADA;

    public static statusEnum getStatusEnumFromString(String statusString, statusEnum defaultValue) {
        if (statusString == null) {
            return defaultValue;
        }

        try {
            return statusEnum.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Se não for possível converter a string em um enum, retorna o valor padrão
            return defaultValue;
        }
    }

    public static statusEnum getStatusEnumFromString(String statusString) {
        return getStatusEnumFromString(statusString, ABERTA);
    }
}

