## Le problème de l'ascenseur

### Le projet

Les habitants de la paisible Résidence des Peupliers vivent une situation assez étrange. Des dysfonctionnements répétés de leur ascenseur leur causent des désagréments dans leur vie quotidienne. Ils font appel à une société de maintenance afin d'y remédier. 

### Fonctionnement de l'ascenseur

L'action se déroule dans la résidence des Peupliers, un immeuble de 4 étages. 

L'ascenseur peut s'arrêter à chaque étage. 

Une personne peut attendre l'ascenseur à chaque étage. Elle peut appeler l'ascenseur en appuyant sur un des deux boutons : 

* le premier permet d'appeler l'ascenseur quand on veut monter;
* le second permet d'appeler l'ascenseur quand on veut descendre. 

Il se passe les choses suivantes lorsque l'ascenseur arrive à un étage. 

1. Les personnes qui souhaitent se rendre à cette étage descendent de l'ascenseur. 
2. L'ascenseur indique s'il monte ou s'il descend. 
3. Si l'ascenseur monte alors les personnes qui attendent à cet étage et qui souhaitent monter embarque dans l'ascenseur. 
4. Si l'ascenseur descend alors les personnes qui attendent à cet étage et qui souhaitent descendre embarque dans l'ascenseur.
5. L'ascenseur ferme ensuite ses portes et monte ou descend d'un étage.  

### Fonctionnement du programme

#### La classe `Building`

Le fonctionnement de l'ascenseur est programmé dans une classe `Building` qui comporte une méthode `main()`.  

Dans un premier temps, la classe `Building` crée un immeuble de 4 étages avec un ascenseur et crée des files d'attente de personnes à chaque étage. Une file d'attente peut être vide. 

#### L'interface `Elevator`

La classe `Building` fonctionne avec une instance de l'interface `Elevator`. Cette interface comporte plusieurs méthodes que la classe `Building` appelle dans l'ordre suivant. La classe `Building` connaît le nombre maximal de personnes que l'ascenseur peut embarquer.

##### Etape 1 : initialisation

En début de programme la méthode `startsAtFloor()` de l'interface `Elevator` est appelée avec le numéro de l'étage duquel l'ascenseur part. Les étages sont numérotés de 1 à 4. 

La méthode `peopleWaiting()` de l'interface `Elevator` est ensuite appelée avec la liste des files d'attente à chaque étape. Pour un immeuble de quatre étages, cette liste comporte donc 4 éléments, un par étage. Chacun de ces éléments est lui-même une liste des personnes qui attendent à cet étage.  

##### Etape 2 : choix de la direction 
Ensuite, la méthode `chooseDirection` est appelée. Elle retourne une instance de l'énumération `DIRECTION` qui comporte trois valeurs : `UP`, `DOWN` et `STOP`. 

Si l'appel à cette méthode retourne `UP` ou `DOWN` alors le programme continue à l'étape 3. Si le retour est `STOP` alors il continue à l'étape 6.  

##### Etape 3 : chargement des personnes
 
La classe `Building` regarde ensuite les personnes qui attendent à l'étage où se trouve l'ascenseur. Elle détermine, en fonction de la direction que prend l'ascenseur, les personnes qui embarquent dans l'ascenseur. Elle appelle alors la méthode `loadPeople()` de l'interface `Elevator` avec en paramètre la liste des personnes qui embarquent dans l'ascenseur. Il se peut que cette liste soit vide. La capacité d'emport de l'ascenseur est prise en compte dans le calcul de cette liste. 

##### Etape 4 : déplacement de l'ascenseur, arrivée à un nouvel étage

La classe `Building` fait alors monter ou descendre l'ascenseur en fonction de la direction qui a été choisie à l'étape 2. Elle appelle la méthode `arriveAtFloor()` de l'interface `Elevator` avec le numéro de l'étage où l'ascenseur arrive.

##### Etape 5 : déchargement des personnes
 
La classe `Building` fait sortir les personnes de l'ascenseur qui souhaitent aller à cet étage. Elle appelle la méthode `unloadPeople()` de l'interface `Elevator` avec en paramètre la liste de personnes qui sortent de l'ascenseur. Il se peut que cette liste soit vide. 

Le programme reprend alors à l'étape 2. 

##### Etape 6 : arrêt de l'ascenseur. 

De même que lors de l'étape 5, la classe `Building` fait sortir les personnes de l'ascenseur qui souhaitent aller à cet étage. Elle appelle la méthode `unloadPeople()` de l'interface `Elevator` avec en paramètre la liste de personnes qui sortent de l'ascenseur. Il se peut que cette liste soit vide.


### Eléments fournis

Le projet fournit comporte les classes suivantes, qui ne doivent pas être modifiées.
- Classe `Building`, `ShadowElevator` et `WaitingList` : ces classes implémentent les étapes décrites.
- Classe `Person` : cette classe modélise une personne qui attend l'ascenseur. Chaque personne possède un nom et l'étage auquel elle veut se rendre. 
- Enumération `DIRECTION` : cette énumération modélise les mouvements possibles de l'ascenseur. 
- Interface `Elevator` : cette interface fixe les méthodes que doit implémenter la classe `DumbElevator`. Elle ne doit pas être modifiée. 
- Classe `DumbElevator` : un exemple d'implémentation très simpliste de l'interface `Elevator`. 

### Implémenter `Elevator`

Le projet consiste à implémenter l'interface `Elevator` et à fournir cette implémentation. 

Pour le moment `Building` utilise la classe `DumbElevator` pour implémenter `Elevator`. On peut modifier la ligne 19: 
```java
Elevator elevator = new DumbElevator(ELEVATOR_CAPACITY);
``` 
Et la remplacer par: 
```java
Elevator elevator = new MyLessDumbElevator(ELEVATOR_CAPACITY);
``` 

À condition bien sûr d'avoir créé la classe `MyLessDumbElevator` qui doit aussi implémenter `Elevator`. 

### Travail à fournir

Pour le moment le DumbElevator n'est pas bien malin. Il charge des personnes à l'étage 1, monte à l'étage 2 et s'arrête. Les habitants de la Résidence des Peupliers ne sont pas contents de ce fonctionnement et préfèrent prendre l'escalier. 

Votre travail consiste à améliorer ce fonctionnement, de façon à garantir que toute personne qui attend l'ascenseur soit amenée à l'étage qu'elle souhaite. Lorsque plus personne n'attend, l'ascenseur doit retourner à l'étage 1 et s'arrêter. 

Dans la pratique, vous devez fournir une implémentation de l'interface `Elevator` qui implémente ce comportement. 

### Remarques

Pour cette première étape, la liste d'attente des personnes est fixée au démarrage du système et ne change pas. 

Le travail sera évalué sur le fait que tout le monde est bien arrivé au bon étage. L'implémentation demandée doit donc ramasser toutes les personnes. Quand l'ascenseur s'arrête, il doit être vide et les files d'attente à chaque étage doivent être vides.    