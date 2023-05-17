# Projet XKube

Dans le cadre du projet de développement informatique - module CSC 3502 - de première année à Télécom SudParis, notre groupe a développé le jeu pour smartphone XKube.

Membres :

- Thomas FOISSY
- Romain MOREAU
- Valentin LANTIGNY
- Basile ROUX

Enseignant responsable :

- Michel SIMATIC

Ce dépôt git regroupe le code source java du projet, développé sous Android Studio, le rapport du projet, et la dernière release.

## Manuel d'installation

Nous recommandons d'utiliser Android Studio, que nous avons utilisé pour développer l'application. 

### Installation

* __Windows__

Vous pouvez télécharger la dernière version d'Android Studio en suivant [ce lien](https://developer.android.com/studio). Il suffit de double cliquer sur le fichier `.exe`. 
Veuillez ensuite télécharger le code source du pojet et d'ouvrir `Projet-dev` dans Android Studio en sélectionnant `file` > `open` > `Projet-dev`.

* __Mac OS__

Vous pouvez télécharger la dernière version d'Android Studio en suivant [ce lien](https://developer.android.com/studio). Il suffit ensuite de lancer le fichier DMG et de glisser-déposer le logiciel dans le dossier `Applications`. Veuillez ensuite télécharger le code source du pojet et d'ouvrir `Projet-dev` dans Android Studio en sélectionnant `file` > `open` > `Projet-dev`.

- __Linux__

**_Debian_** (et autre distribution fondé sur Debian)

Deux options sont possibles.
<ins>Via archive tar.gz :</ins> Téléchargez l'archive [ici]([https://developer.android.com/studio#downloads](https://developer.android.com/studio#downloads)). Allez ensuite dans le dossier où se trouve l'archive, et éxécutez

`$ tar -xvzf android-studio-xxx-linux.tar.gz`

Pour éxécutez le programme :

`$  /opt/android-studio/bin/studio.sh &`

<ins>Via Snap :</ins> Installez Snap puis Android Studio :

`$ sudo apt update && sudo apt install snapd`

`$ sudo snap install core` 

`$ sudo snap install android-studio --classic`

`$ snap run android-studio`

**_Arch_** (et autre distribution fondé sur Arch)

Android Studio est disponible sur l'AUR. 
Avec yaourt :

`$ yaourt -S android-studio`

 
Dans les deux cas, veuillez ensuite cloner le dépôt git :

`$ git clone https://gitlab.com/Racoon-r/Projet-dev.git`

Vous pouvez ouvrir le projet en sélectionnant `file` > `open` > `Projet-dev` et en sélectionnant `Projet-dev`.

**Attention, le chemin d'accès à SDK change en fonction de la distribution.**

Le fichier à modifier se trouve dans `local.properties`.
_Sous Debian_, `sdk.dir=/home/<_user_>/Android/Sdk`
_Sous Arch_,  `sdk.dir=/opt/android-sdk`


- __Android__

Veuillez installer le fichier .apk présent dans le dossier `Release`.
Il faut probablement autoriser l'installation d'applications étrangère ne venant pas du PlayStore.

## Manuel d'utilisation

- **Utiliser le code Java**

_Nota Bene : il est nécessaire d'avoir Gradle installé. La version utilisé pour la release de mai 2023 est Gradle JDK 11.0.19._

Nous utilisons 4 sous-dossiers : `android`, `core`, `desktop` et `Gradle Scripts`.

- _Android :_ Contient le dossier _assets_ où se trouve les différentes textures graphiques. Le dossier V2 correspond aux nouvelles textures plus récentes.
- _Core_ : Dans `main/java/com.mygdx.xcube/` se trouve le code principal.

| Classe Java  | Description |
| :----------: | :----------|
|XCube				 |Permet de lancer le jeu|
|MainMenuScreen|Lance le menu principal, permet de choisir le mode de jeu|
|Terrain			 |Crée et initialise les différents blocs cliquable|
|GameScreen		 |Assure l'affichage de la partie ; gère les intéractions|
|Multiplayer	 |Mise en place du multijoueur|
|EndScreen		 |Contient les conditions de victoire & les écrans de fins|
|/blocks			 |Différentes classes pour insérer les bouttons et les éléments de jeu|

- _Desktop_ : `Dans main/java/com.mygdx.xcube/`, la classe DesktopLauncher permet de lancer le jeu sur ordinateur.

- _Gradle Scripts_ : On trouve ici les différents fichiers pour modifier les paramètres du projet, notamment le changement de SDK.

Les différents rendus graphique pour le rapport et pour le style des objets intervenant dans le jeu sont effectués sur _Adobe Illustrator_.

En cas de problème avec Gradle, il faut s'assurer d'avoir la bonne version dans :

`File > Settings... > Build, Execution, Deployment > Build Tools > Gradle`

Il est nécessaire de recharger Gradle :

`File > Sync Project with Gradle Files ...`

Dans le cas d'une utilisation sous Arch Linux, il est sûrement nécessaire de changer le fichier `local.properties` comme mentionné précédemment. 

Couleurs :

|Couleur | Code hex. |
|:-----|:-----:|
|Bleu foncé|#0037F4|
|Bleu bord de mer|#029FFA|
|Rouge crépitant|#B1160A|
|Rouge saumon|#E07C6D|

- **Utiliser l'application XKube**

Dans le menu principal : 

| Bouton | Description |
| :---------:|:--------|
|DLC Activés / Désactivés | Permet de jouer avec ou sans le DLC. Cela fait apparaître un bloc en plus sur la grille de manière aléatoire.|
|Local | Jouer en local. Nécessite d'avoir deux personnes derrière l'écran, le jeu passe directement d'une couleur à l'autre.| 
|Multijoueur|Permet de jouer en ligne. Le premier joue avec le deuxième, etc. Principe d'une file d'attente|
|Intelligence Artificielle|Mode de jeu contre une IA sous le principe du MinMax.|

Une fois en jeu :

La partie commence avec le·a joueur·se bleu·e. On a deux coups. En touchant une barre grise, elle se transforme en bleu clair. Une fois le tour terminé, cela passe au rouge. Le bleu devient ensuite bleu foncé au tour prochain. Quand trois croix rouge ou bleue sont allignées, la partie est terminée. 

## Release
