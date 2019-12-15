# First_Application_Android_News_App

## Yassine Abderrahmani 

## Les choses faites 

### Remarques :

Il y a un nombre MAX de requêtes possibles qui est de : 250 toutes les 12H ... 
Cela créer une erreur 'internet' dans l'application si elle est atteinte.

- **Splashscreen**
    - Récupération des différentes Sources -> Puis stocker pour être utiliser dans un SPINNER dans le MainActivity
    - Prise en comtpe des erreurs si il n'y a pas internet avec une boite de dialogue (Retry, Close, Rester dessus)
    - Modification du background : icone, couleur...
  
- **Main Activity**
    - Chargement des données Json
    - Prise en compte des erreurs si il n'y a pas internet : boite de dialogue (Retry, Close)
    - Mise en Forme Conditionnelle : PAIR & IMPAIR 
    - Adaptation selon si le contenu est présent ou non : Image Spécifique + Texte spécifique en cas d'absence
    - Ouverture d'une fenêtre de détail si on clique sur un article (SCROLLing possible)
    - Webview si on clique sur le bouton dédié dans la fenêtre de détail (prise en compte de javascript et donc intéraction avec le contenu possible)

- **Chauses non faites**
    - Loading des pages suivantes non pris en comtpes
    - Loader dans la webview non implémenté car j'ai été ralenti par le problème des requetes MAX atteintes... ainsi je n'ai pas pu tester cette fonctionnalité et été contraint de la retirer...
    - Je n'ai pas utilisé les Fragments non plus
