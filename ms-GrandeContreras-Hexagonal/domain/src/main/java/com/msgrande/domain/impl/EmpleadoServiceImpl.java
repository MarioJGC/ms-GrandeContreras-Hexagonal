package com.msgrande.domain.impl;

import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.domain.aggregates.request.EmpleadoRequest;
import com.msgrande.domain.aggregates.response.ResponseBase;
import com.msgrande.domain.ports.in.EmpleadoServiceIn;
import com.msgrande.domain.ports.out.EmpleadoServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;

    @Override
    public ResponseBase createEmployeeIn(EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.createEmployeeOut(empleadoRequest);
    }

    @Override
    public ResponseBase findEmployeeIn(String numDoc) {
        return empleadoServiceOut.findEmployeeOut(numDoc);
    }

    @Override
    public ResponseBase findAllIn() {
        return empleadoServiceOut.findAllOut();
    }

    @Override
    public ResponseBase UpdateEmployeeIn(EmpleadoDto empleadoDto) {
        return empleadoServiceOut.UpdateEmployeeOut(empleadoDto);
    }

    @Override
    public ResponseBase deleteEmployeeIn(EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.deleteEmployeeOut(empleadoRequest);
    }
}
