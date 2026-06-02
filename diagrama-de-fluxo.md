```mermaid
flowchart TD

A([Início]) --> B[Usuário abre o app]

B --> C{Possui conta?}

C -->|Sim| D[Tela de Login]
D --> E[Inserir email e senha]
E --> F[Entrar]

C -->|Não| G[Tela de Cadastro]
G --> H[Inserir email]
H --> I[Inserir senha]
I --> J[Confirmar senha]
J --> K[Criar conta]

F --> L[Ir para Dashboard]
K --> L

subgraph Dashboard
  M[Visualiza livro atual]
  N[Visualiza progresso de páginas]
  O[Visualiza ofensiva de dias]
end

L --> M
L --> N
L --> O

L --> P{Deseja adicionar livro?}
P -->|Sim| Q[Abrir tela adicionar livro]
Q --> R[Inserir nome]
R --> S[Inserir total de páginas]
S --> T[Salvar]

L --> U{Deseja atualizar leitura?}
U -->|Sim| V[Abrir livro atual]
V --> W[Inserir página atual]
W --> X[Salvar progresso]

T --> Y[Atualizar estatísticas]
X --> Y

Y --> Z[Atualizar ofensiva de dias]

Z --> AA{Deseja ver biblioteca?}
AA -->|Sim| AB[Abrir biblioteca]
AB --> AC[Visualizar livros cadastrados]

AC --> AD([Fim])
Z --> AD
