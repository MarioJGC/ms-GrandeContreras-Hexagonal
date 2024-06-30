package com.msgrande.infraestructure.adapters;

import com.msgrande.infraestructure.models.EmpleadoException;
import com.msgrande.domain.aggregates.constants.Constants;
import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.domain.aggregates.request.EmpleadoRequest;
import com.msgrande.domain.aggregates.response.ResponseBase;
import com.msgrande.domain.aggregates.response.ResponseReniec;
import com.msgrande.domain.ports.out.EmpleadoServiceOut;
import com.msgrande.infraestructure.dao.EmpleadoRepository;
import com.msgrande.infraestructure.entity.EmpleadoEntity;
import com.msgrande.infraestructure.mapper.EmpleadoMapper;
import com.msgrande.infraestructure.rest.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {

    private final ClienteReniec clienteReniec;
    private final EmpleadoMapper empleadoMapper;
    private final EmpleadoRepository empleadoRepository;

    @Value("${token}")
    private String token;

    @Override
    public ResponseBase createEmployeeOut(EmpleadoRequest empleadoRequest) {
        boolean exist = empleadoRepository.existsByNumDoc(empleadoRequest.getNumDoc());
        if (exist)
        {
            throw new EmpleadoException("Error, el empleado ya existe");
        }else {
            EmpleadoEntity empleado = getEmployeeEntity(empleadoRequest);
            if (empleado != null){
                return new ResponseBase(Constants.RESPUESTA_EXITOSA,
                        Constants.MENSAJE_INSERCCION_EXITOSA,
                        Optional.of(empleadoMapper.mapToDto(empleado)));
            }
            return new ResponseBase(Constants.RESPUESTA_ERRONEA, Constants.MENSAJE_INSERCCION_ERRONEA, Optional.empty());
        }
    }

    @Override
    public ResponseBase findEmployeeOut(String numDoc) {
        Optional<EmpleadoEntity> empleadoEntity = empleadoRepository.findByNumDoc(numDoc);
        if (empleadoEntity.isPresent()){
            EmpleadoDto empleadoDto = empleadoMapper.mapToDto(empleadoEntity.get());
            return new ResponseBase(Constants.RESPUESTA_EXITOSA,Constants.USUARIO_ENCONTRADO,Optional.of(empleadoDto));
        }
        return new ResponseBase(Constants.RESPUESTA_ERRONEA,Constants.USUARIO_NO_ENCONTRADO,Optional.empty());
    }

    @Override
    public ResponseBase findAllOut() {
        List<EmpleadoDto> empleados = empleadoMapper.mapToDtoList(empleadoRepository.findAll().stream().filter(e -> e.getEstado() == 1).toList());
        if (!empleados.isEmpty()){
            return new ResponseBase(Constants.RESPUESTA_EXITOSA,Constants.LISTA_DE_USUARIOS,Optional.of(empleados));
        }
        return new ResponseBase(Constants.RESPUESTA_EXITOSA, Constants.LISTA_DE_USUARIOS_VACIA, Optional.empty());
    }

    @Override
    public ResponseBase UpdateEmployeeOut(EmpleadoDto empleadoDto) {
        Optional<EmpleadoEntity> empleadoEntity = empleadoRepository.findByNumDoc(empleadoDto.getNumDoc());
        if (empleadoEntity.isPresent()){
            EmpleadoEntity empleado = empleadoEntity.get();
            empleado.setEdad(empleadoDto.getEdad());
            empleado.setCargo(empleadoDto.getCargo());
            empleado.setSalario(empleadoDto.getSalario());
            empleado.setTelefono(empleadoDto.getTelefono());
            empleado.setCorreo(empleado.getCorreo());
            empleado.setDepartamento(empleado.getDepartamento());
            empleado.setTelefono(empleado.getTelefono());
            empleado.setDateUdpate(getTime());
            empleado.setUsuaUpdate(Constants.USER_MODIFY);
            empleadoRepository.save(empleado);
            return new ResponseBase(Constants.RESPUESTA_EXITOSA,Constants.USUARIO_ACTUALIZADO,Optional.of(empleadoMapper.mapToDto(empleado)));
        }
        return new ResponseBase(Constants.RESPUESTA_ERRONEA,Constants.USUARIO_NO_ENCONTRADO,Optional.empty());
    }

    @Override
    public ResponseBase deleteEmployeeOut(EmpleadoRequest empleadoRequest) {
        Optional<EmpleadoEntity> empleadoEntity = empleadoRepository.findByNumDoc(empleadoRequest.getNumDoc());
        if (empleadoEntity.isPresent()){
            EmpleadoEntity empleado = empleadoEntity.get();
            empleado.setEstado(Constants.ESTADO_INACTIVO);
            empleado.setDateDelete(getTime());
            empleado.setUsuaDelete(Constants.USER_DELETE);
            empleadoRepository.save(empleado);
            return new ResponseBase(Constants.RESPUESTA_EXITOSA, Constants.USUARIO_ELIMINADO,Optional.of(empleado));
        }
        return new ResponseBase(Constants.RESPUESTA_ERRONEA, Constants.USUARIO_NO_ENCONTRADO,Optional.empty());
    }

    private EmpleadoEntity getEmployeeEntity(EmpleadoRequest empleadoRequest){

        ResponseReniec response = getExec(empleadoRequest.getNumDoc());
        if (response != null){
               EmpleadoEntity empleado = new EmpleadoEntity();
               empleado.setNombres(response.getNombres());
               empleado.setApellidoPaterno(response.getApellidoPaterno());
               empleado.setApellidoMaterno(response.getApellidoMaterno());
               empleado.setTipoDoc(response.getTipoDocumento());
               empleado.setNumDoc(response.getNumeroDocumento());
               empleado.setEstado(Constants.ESTADO_ACTIVO);
               empleado.setDateCreate(getTime());
               empleado.setUsuaCrea(Constants.USU_ADMIN);
               return empleadoRepository.save(empleado);
        }
        return null;
    }

    private Timestamp getTime(){
        long current = System.currentTimeMillis();
        return new Timestamp(current);
    }

    private ResponseReniec getExec(String numero){
        String header = "Bearer " + token;
        return clienteReniec.getInfoReniec(numero, header);
    }


}
