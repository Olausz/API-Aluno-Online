package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.DisciplinaAlunoResponse;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.enums.MatriculoAlunoStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    public static final double MEDIA_PARA_APROVAÇÃO = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculoAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula Aluno" +
                                "não encontrada"));

        if (!MatriculoAlunoStatusEnum.MATRICULADO.equals(matriculaAluno.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possivel trancar " +
                    "uma matricula com o status MATRICULADO");
        }

        matriculaAluno.setStatus(MatriculoAlunoStatusEnum.TRANCADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void atualizaNotas(Long matriculaAlunoId,
                              AtualizarNotasRequest atualizarNotasRequest) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula Aluno" +
                                "não encontrada"));

        if (atualizarNotasRequest.getNota1() != null) {
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }

        if (atualizarNotasRequest.getNota2() != null) {
            matriculaAluno.setNota1(atualizarNotasRequest.getNota2());
        }

        calcularMedia(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    private void calcularMedia(MatriculaAluno matriculaAluno) {
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if (nota1 != null && nota2 != null) {
            Double media = (nota1 + nota2) / 2;
            matriculaAluno.setStatus(media > MEDIA_PARA_APROVAÇÃO ? MatriculoAlunoStatusEnum.APROVADO :
                    MatriculoAlunoStatusEnum.REPROVADO);
        }
    }

    public HistoricoAlunoResponse emitirHistorico(Long alunoId) {
        List<MatriculaAluno> matriculasDoAluno = matriculaAlunoRepository.findByAlunoId(alunoId);

        if (matriculasDoAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matriculas");
        }

        HistoricoAlunoResponse historicoAluno = new HistoricoAlunoResponse();
        historicoAluno.setNomeAluno(matriculasDoAluno.get(0).getAluno().getNome());
        historicoAluno.setEmailAluno(matriculasDoAluno.get(0).getAluno().getEmail());
        historicoAluno.setCpfAluno(matriculasDoAluno.get(0).getAluno().getCpf());

        List<DisciplinaAlunoResponse> disciplinasList = new ArrayList<>();

        for (MatriculaAluno matriculaAluno : matriculasDoAluno) {
            DisciplinaAlunoResponse disciplinaAlunoResponse = new DisciplinaAlunoResponse();
            disciplinaAlunoResponse.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
            disciplinaAlunoResponse.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
            disciplinaAlunoResponse.setNota1(matriculaAluno.getNota1());
            disciplinaAlunoResponse.setNota1(matriculaAluno.getNota2());

            if (matriculaAluno.getNota1() != null && matriculaAluno.getNota2() != null) {
                disciplinaAlunoResponse.setMedia((matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2.0);
            } else {
                disciplinaAlunoResponse.setMedia(null);
            }

            disciplinaAlunoResponse.setStatus(matriculaAluno.getStatus());
        }

        historicoAluno.setDisciplinaAlunoResponses(disciplinasList);

        return historicoAluno;

    }


}
