package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.dto.AlumnoDto;
import com.javainuse.bootmysqlcrud.dto.ApoderadoDto;
import com.javainuse.bootmysqlcrud.dto.CuotaMatriculaDto;
import com.javainuse.bootmysqlcrud.dto.SeguimientoSesionDto;
import com.javainuse.bootmysqlcrud.dto.TalleresMatriculadosDto;
import com.javainuse.bootmysqlcrud.service.AlumnoService;
import com.javainuse.bootmysqlcrud.service.ApoderadoService;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/api/v1/alumno"})
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ApoderadoService apoderadoService;

    @GetMapping({"/listar"})
    public ResponseEntity<ResponseWrapper> lista() {
        List<AlumnoDto> listaAlumno = this.alumnoService.listar();
        // En cada alumno, cargar sus apoderados
        for (AlumnoDto alumno : listaAlumno) {
            List<ApoderadoDto> listaApoderado = this.apoderadoService.listar(alumno.getIdAlumno());
            alumno.setApoderados(listaApoderado);
        }
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAlumno, "Lista de Alumnos"));
    }

    @GetMapping({"/obtener/{idAlumno}"})
    public ResponseEntity<ResponseWrapper> obtener(@PathVariable Integer idAlumno) {
        List<AlumnoDto> listaAlumno = this.alumnoService.listar();
        AlumnoDto alumno = listaAlumno.stream().filter(r -> r.getIdAlumno().equals(idAlumno)).findFirst().orElse(null);
        if (alumno == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.NOT_FOUND).body(new ResponseWrapper(null, "Alumno no encontrado"));
        List<ApoderadoDto> listaApoderado = this.apoderadoService.listar(idAlumno);
        alumno.setApoderados(listaApoderado);
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(alumno, "Lista Alumno"));
    }

    @PostMapping({"/registrar"})
    public ResponseEntity<ResponseWrapper> registrar(@RequestBody AlumnoDto alumno) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer idAlumno = this.alumnoService.registrar(alumno, user);
        if (idAlumno == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el alumno"));
        List<ApoderadoDto> listaApoderado = this.apoderadoService.listar(alumno.getIdAlumno());
        if (alumno.getApoderados() != null && !alumno.getApoderados().isEmpty())
            for (ApoderadoDto apoderadoDto : alumno.getApoderados()) {
                apoderadoDto.setIdAlumno(alumno.getIdAlumno());
                Integer idApoderado = apoderadoDto.getIdApoderado();
                Integer resultApoderado = null;
                apoderadoDto.setIdAlumno(idAlumno);
                if (idApoderado != null && idApoderado.intValue() > 0) {
                    resultApoderado = this.apoderadoService.editar(apoderadoDto, user);
                } else {
                    resultApoderado = this.apoderadoService.registrar(apoderadoDto, user);
                }
                if (resultApoderado == null)
                    return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el apoderado"));
            }
        return ResponseEntity.status((HttpStatusCode) HttpStatus.CREATED).body(new ResponseWrapper(idAlumno, "alumno registrado con éxito"));
    }

    @PutMapping({"/editar"})
    public ResponseEntity<ResponseWrapper> editar(@RequestBody AlumnoDto alumno) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer idAlumno = this.alumnoService.editar(alumno, user);
        if (idAlumno == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al editar el alumno"));
        List<ApoderadoDto> listaApoderado = this.apoderadoService.listar(alumno.getIdAlumno());
        if (alumno.getApoderados() != null && !alumno.getApoderados().isEmpty())
            for (ApoderadoDto apoderadoDto : alumno.getApoderados()) {
                apoderadoDto.setIdAlumno(alumno.getIdAlumno());
                Integer idApoderado = apoderadoDto.getIdApoderado();
                Integer result = null;
                if (idApoderado != null && idApoderado.intValue() > 0) {
                    result = this.apoderadoService.editar(apoderadoDto, user);
                } else {
                    result = this.apoderadoService.registrar(apoderadoDto, user);
                }
                if (result == null)
                    return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al registrar el apoderado"));
            }
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(idAlumno, "Alumno editado con éxito"));
    }

    @DeleteMapping({"/eliminar/{idAlumno}"})
    public ResponseEntity<ResponseWrapper> eliminar(@PathVariable Integer idAlumno) {
        Integer result = this.alumnoService.eliminar(idAlumno);
        if (result == null || result.intValue() <= 0)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al eliminar el alumno"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(result, "Alumno eliminado con éxito"));
    }

    @GetMapping({"/talleresMatriculados/{idAlumno}"})
    public ResponseEntity<ResponseWrapper> talleresMatriculados(@PathVariable Integer idAlumno) {
        List<TalleresMatriculadosDto> listaAlumno = this.alumnoService.listarTalleresMatriculados(idAlumno);
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAlumno, "Lista Alumno"));
    }

    @GetMapping({"/seguimientoTaller/{idMatricula}"})
    public ResponseEntity<ResponseWrapper> seguimientoTaller(@PathVariable Integer idMatricula) {
        List<SeguimientoSesionDto> listaAlumno = this.alumnoService.listaSeguimientoTaller(idMatricula);
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAlumno, "Lista seguimiento Taller"));
    }

    @PostMapping({"/actualizarSegimientoAlumno"})
    public ResponseEntity<ResponseWrapper> actualizarSegimientoAlumno(@RequestBody List<SeguimientoSesionDto> param) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer result =0;
        for (SeguimientoSesionDto seguimiento : param) {
              result = this.alumnoService.actualizarSegimientoAlumno(seguimiento, user);
            if (result == null)
                return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al actualizar el seguimiento del alumno"));
        }
          return ResponseEntity.status((HttpStatusCode) HttpStatus.CREATED).body(new ResponseWrapper(result, "Seguimiento del alumno actualizado con éxito"));
    }

    @GetMapping({"/cuotasMatricula/{idMatricula}"})
    public ResponseEntity<ResponseWrapper> cuotasMatricula(@PathVariable Integer idMatricula) {
        List<CuotaMatriculaDto> listaAlumno = this.alumnoService.listaCuotasMatricula(idMatricula);
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAlumno, "Lista cuotas Taller"));
    }

    @PostMapping({"/registrarCuotaMatricula"})
    public ResponseEntity<ResponseWrapper> registrarCuotaMatricula(@RequestBody CuotaMatriculaDto param) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer result = this.alumnoService.registrarCuotaMatricula(param, user);
        if (result == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al actualizar el seguimiento del alumno"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.CREATED).body(new ResponseWrapper(result, "Cuotas del alumno actualizado con éxito"));
    }

    @PostMapping({"/registrarCuotaDetMatricula"})
    public ResponseEntity<ResponseWrapper> registrarCuotaDetMatricula(@RequestBody CuotaMatriculaDto param) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer result = this.alumnoService.registrarCuotaDetMatricula(param, user);
        if (result == null)
            return ResponseEntity.status((HttpStatusCode) HttpStatus.BAD_REQUEST).body(new ResponseWrapper(null, "Error al actualizar el seguimiento del alumno"));
        return ResponseEntity.status((HttpStatusCode) HttpStatus.CREATED).body(new ResponseWrapper(result, "Cuotas del alumno actualizado con éxito"));
    }

    @PostMapping("/registrarPagosDetMatricula")
    public ResponseEntity<ResponseWrapper> registrarPagosDetMatricula(@RequestBody List<CuotaMatriculaDto> pagos) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        for (CuotaMatriculaDto pago : pagos) {
            Integer result = alumnoService.registrarCuotaDetMatricula(pago, user);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper(null, "Error al registrar el pago de la cuota"));
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper(true, "Pagos de cuotas registrados con éxito"));
    }

    @GetMapping({"/listarAlumnosDetallexSession/{idAperturaTaller}/{idSesion}"})
    public ResponseEntity<ResponseWrapper> seguimientoTaller(@PathVariable Integer idAperturaTaller, @PathVariable Integer idSesion) {
        List<SeguimientoSesionDto> listaAlumno = this.alumnoService.ListarAlumnosDetallexSession(idAperturaTaller, idSesion);
        return ResponseEntity.status((HttpStatusCode) HttpStatus.OK).body(new ResponseWrapper(listaAlumno, "Lista seguimiento Taller x Session"));
    }
}