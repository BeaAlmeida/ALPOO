use unib_db;

/* O departamento de recursos humanos precisa de um relatório de todos
os alunos e os seus respectivos cursos. Crie uma consulta que exiba o
nome do responsável, o nome do aluno, o CPF, o nome do curso, o tipo
de curso, a carga horária e a quantidade de disciplinas do aluno. */

select R.nm_resp, A.nm_aluno, A.cd_cpf_aluno, C.ds_curso, C.ds_tipo_curso, C.qt_ch_curso, count(CD.cd_curso) qt_disciplinas
from responsavel R
	join aluno A
	on R.cd_aluno = A.cd_aluno
		join curso C
        on A.cd_curso = C.cd_curso
			join curso_has_disciplina CD
            on C.cd_curso = CD.cd_curso
group by CD.cd_curso;

/* O departamento de recursos humanos precisa de um relatório de todas as
disciplinas com os professores que as lecionam e o curso a que pertencem.
Crie uma consulta que exiba o nome do professor, o titulo do professor, o
nome da disciplina, o nome do curso, o tipo de curso e o nome do coordenador.
Exiba também as disciplinas que não tenham um professor ou não estejam em
um curso ou que o curso não tenha um coordenador. */

select P.nm_prof, P.ds_titulo_prof, D.ds_disci, Cu.ds_curso, Cu.ds_tipo_curso, Co.nm_coord
from coordenador Co
	right join curso Cu
    on Co.cd_curso =  Cu.cd_curso
		join curso_has_disciplina CD
        on Cu.cd_curso = CD.cd_curso
			right join disciplina D
            on CD.cd_disci = D.cd_disci
				left join disciplina_has_professor DP
                on D.cd_disci = DP.cd_disci
					left join professor P
					on DP.cd_prof = P.cd_prof;
                    
/* Faça uma listagem de todos os alunos de Sistemas de Informação que
foram aprovados (média maior ou igual a sete) em Cálculo. */

select cd_aluno, nm_aluno
from aluno
where cd_curso in (
	select cd_curso
    from curso
    where ds_curso = 'sistemas de informação'
) and cd_aluno in (
	select cd_aluno
    from aluno_has_disciplina
    where cd_disci in (
		select cd_disci
        from disciplina
        where ds_disci = 'cálculo'
	) and qt_media >= 7
);