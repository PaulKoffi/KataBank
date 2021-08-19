#language: fr
Fonctionnalité: Créditer son compte

  Contexte:
    Etant donné un client "paul", "koffi" qui vient de créer son compte


  Scénario: Créditer son compte
    Quand paul vérifie le solde de son compte, il est de "0" euros
    Quand paul crédite son compte le "11/11/2021" de "500" euros
    Et    que la banque procède à l'opération
    Alors son solde à la banque est maintenant de "500" euros





