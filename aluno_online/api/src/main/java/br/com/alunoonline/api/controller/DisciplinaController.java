package br.com.alunoonline.api.controller;

import java.util.List;
import java.util.Optional;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/disciplinas")
@RestController
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void criarDisciplina(@RequestBody Disciplina disciplina){
        disciplinaService.criarDisciplina(disciplina);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarTodosDisciplinas() {
        return disciplinaService.listarTodosDisciplinas();
    }

    @GetMapping("/professor/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarDisciplinaDoProf(@PathVariable Long professorId){
        return disciplinaService.listarDisciplinasDoProf(professorId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Disciplina> buscarDisciplinaPorId(@PathVariable Long id) {
        return disciplinaService.buscarDisciplinaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDisciplinaPorId(@PathVariable Long id) {
        disciplinaService.deletarDisciplinaPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarDisciplinaPorID(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplinaService.atualizarDisciplinaPorID(id, disciplina);
    }

}
