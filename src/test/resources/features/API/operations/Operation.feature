#language: fr
Fonctionnalité: Lister les opérations sur son compte

  Contexte:
    Etant donné le client "paul", "Gilbert" qui vient de créer son compte en banque


  Scénario: Créditer son compte puis Retrait de son compte et lister les opérations
    Quand il vérifie le solde du compte, il est de "0" euros
    Quand il crédite le compte le "11/11/2021" de "500" euros
    Et    que la banque exécute l'opération
    Alors son solde à la banque est de "500" euros et
    Quand il effectue ensuite un retrait de son compte le "12/11/2021" de "300" euros
    Et    que la banque effectue l'opération,
    Alors son solde à la banque affiche maintenant "200" euros
    Quand  il consulte les opérations de son compte
    Alors il y'a un crédit de "500" euros le "11/11/2021", la balance est alors de "500"
    Et    il y'a un retrait de "300" euros le "12/11/2021", la balance est alors de "200"