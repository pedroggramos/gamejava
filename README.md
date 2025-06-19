🎮 Harry Potter - Mini RPG Educacional
📘 Trabalho de Programação de Computadores III
📅 Entrega: 17 de junho de 2025

👨‍💻 Desenvolvedor:
- Pedro Gabriel Guimarães Ramos
 
---

🎯 OBJETIVO DO JOGO

Avançar pelas fases do mapa respondendo corretamente às perguntas para colocar o brasão da sua casa nas etapas da fase e acumular pontos. Ao final do jogo, vence quem tiver mais pontos.

---

🧙‍♂️ PREPARAÇÃO

- Cada jogador escolhe uma casa de Hogwarts:
  - Grifinória 🦁
  - Sonserina 🐍
  - Corvinal 🦅
  - Lufa-Lufa 🦡

- Os brasões das casas são exibidos na parte inferior do mapa, na ordem definida para os turnos.

- A ordem dos jogadores é sorteada aleatoriamente e exibida.

---

📜 COMO JOGAR

- O jogo é dividido em 9 fases.
- Cada fase tem 3 etapas (representadas por quadradinhos no mapa).
- Apenas a fase atual está disponível para jogadas.
- No turno do jogador:
  - Ele clica em uma etapa da fase.
  - Uma pergunta de múltipla escolha é exibida.
  - Se acertar:
    - Ganha 1 ponto.
    - O brasão da casa é colocado naquela etapa.
  - Se errar:
    - Perde o turno.
    - Nada é marcado.

- Ao completar as 3 etapas da fase, o jogo avança para a próxima.

---

♻️ TURNOS E PONTUAÇÃO

- O jogo roda em turnos entre os jogadores.
- Cada etapa vale 1 ponto.
- O turno avança para o próximo jogador após cada tentativa (acerto ou erro).

---

🏁 FIM DO JOGO

- O jogo termina quando todas as 9 fases forem completadas.
- Um ranking final é exibido com a pontuação dos jogadores.
- O jogador com mais pontos vence!

---

💾 COMO EXECUTAR

1. Abra o projeto no Eclipse.
2. Execute a classe `Game.java`.
3. O banco de dados e as tabelas são criados automaticamente (MySQL porta 3306).
4. As perguntas são carregadas do banco conforme o jogo avança.
5. Todas as imagens devem estar na pasta `assets/`.

---

🛠️ DETALHES TÉCNICOS

- Linguagem: Java
- Interface gráfica: `javax.swing` e `java.awt`
- Lógica e estrutura: `java.util` (listas, eventos, etc.)
- Conexão com banco de dados: `java.sql`
- Banco: MySQL (criado automaticamente pela classe `InicioBD.java`)

---

📌 OBSERVAÇÕES FINAIS

- Esse jogo foi desenvolvido para fins educacionais e acadêmicos.
- Todos os recursos visuais são de uso temporário para apresentação do trabalho.
