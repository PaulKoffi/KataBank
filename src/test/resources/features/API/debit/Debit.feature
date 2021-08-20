#language: fr
Fonctionnalité: Effectuer un retrait de son compte

  Contexte:
    Etant donné le client "paul", "lombert" qui vient de créer son compte


  Scénario: Créditer son compte puis Retrait de son compte
    Quand il vérifie le solde de son compte, il est de "0" euros
    Quand il crédite son compte le "11/11/2021" de "500" euros
    Et    que la banque procède ensuite à l'opération
    Alors son solde à la banque est de "500" euros
    Quand il effectue un retrait de son compte le "11/11/2021" de "300" euros
    Et    que la banque effectue l'opération
    Alors son solde à la banque affiche "200" euros

