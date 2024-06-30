package com.msgrande.domain.ports.out;

import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.domain.aggregates.request.EmpleadoRequest;
import com.msgrande.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceOut {

    ResponseBase createEmployeeOut(EmpleadoRequest empleadoRequest);
    ResponseBase findEmployeeOut(String numDoc);
    ResponseBase findAllOut();
    ResponseBase UpdateEmployeeOut(EmpleadoDto empleadoDto);
    ResponseBase deleteEmployeeOut(EmpleadoRequest empleadoRequest);
}
