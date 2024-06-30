package com.msgrande.domain.ports.in;

import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.domain.aggregates.request.EmpleadoRequest;
import com.msgrande.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceIn {

    ResponseBase createEmployeeIn(EmpleadoRequest empleadoRequest);
    ResponseBase findEmployeeIn(String numDoc);
    ResponseBase findAllIn();
    ResponseBase UpdateEmployeeIn(EmpleadoDto empleadoDto);
    ResponseBase deleteEmployeeIn(EmpleadoRequest empleadoRequest);
}
