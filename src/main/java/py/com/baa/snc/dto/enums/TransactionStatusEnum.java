package py.com.baa.snc.dto.enums;

public enum TransactionStatusEnum {
    PENDING, // Se espera confirmacion del usario
    CANCELED, // El usuario cancelo voluntariamente o porque se agrego otro
    // pedido
    USER_CONFIRMED, // El usuario confirmo que desea realizar la operación
    // pero aún no se realizo en JCard
    CONFIRMED, // La operacion ya se realizo en JCard. Estado final
    INSUFFICIENT_FUNDS, // No tenia fondos suficientes al momento de
    // realizar la operacion
    TIME_OUT, // No se realizó dentro del tiempo esperado
    REVERSED,
    ERROR,// Ocurrio algun otro error inesperado,
    NOT_APPROVED, //Transaccion no procesada por JCard, irc != 0000
    APPROVED, //transaccion procesada
    TIMEOUT,
    INFORMATIVE
}
