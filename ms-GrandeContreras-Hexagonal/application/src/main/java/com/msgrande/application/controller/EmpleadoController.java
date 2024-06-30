package com.msgrande.application.controller;

import com.msgrande.domain.aggregates.constants.Constants;
import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.domain.aggregates.request.EmpleadoRequest;
import com.msgrande.domain.aggregates.response.ResponseBase;
import com.msgrande.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v2/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase> createEmployee(@RequestBody EmpleadoRequest empleado){
        ResponseBase responseBase = empleadoServiceIn.createEmployeeIn(empleado);
        if (responseBase.getCode() == Constants.RESPUESTA_EXITOSA){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBase);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
    }

    @GetMapping("/buscar/{numDoc}")
    public ResponseEntity<ResponseBase> findEmployee(@PathVariable String numDoc){
        ResponseBase responseBase = empleadoServiceIn.findEmployeeIn(numDoc);
        if (responseBase.getCode() == Constants.RESPUESTA_EXITOSA){
            return ResponseEntity.status(HttpStatus.OK).body(responseBase);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBase);
    }

    @GetMapping("/todos/")
    public ResponseEntity<ResponseBase> findAllEmployee(){
         return ResponseEntity.status(HttpStatus.OK).body(empleadoServiceIn.findAllIn());
    }

    @PutMapping("/actualizar/")
    public ResponseEntity<ResponseBase> updatebyNumDoc(@RequestBody EmpleadoDto empleado){
        ResponseBase response = empleadoServiceIn.UpdateEmployeeIn(empleado);
        if (response.getCode() == Constants.RESPUESTA_EXITOSA){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/eliminar/")
    public ResponseEntity<ResponseBase> deleteEmployee(@RequestBody EmpleadoRequest request){
        ResponseBase response = empleadoServiceIn.deleteEmployeeIn(request);
        if (response.getCode() == Constants.RESPUESTA_EXITOSA){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
