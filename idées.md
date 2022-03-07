# map:
- list[nourriture] (taille n) 
- list[blob] (taille p)
-----------------------------------
- fonction newGen qui lance un cycle 

# nourriture:
-coordX: int (0:longueur fenetre)
-coordY: int (0:hauteur fenetre)


# blob:
-coordX: int (0:longueur fenetre)
-coordY: int (0:hauteur fenetre)
-acceleration: float 
-vitesse: float
-direction: float
-nourriture: int "la quantité de nourriture mangé durant le cycle entre 0 et 2"
-coef vitesse: float "plus le coef est grand plus il va vite"
-coef masse: float "plus la masse est grande plus la vitesse est faible mais plus la force est grande"
-coef d'instinct de survie: float "moins il reste de nourriture disponible plus le blob avec 0 de nourriture va vite"
-coef d'energie: float "le blob pert de l'energie quand il avance et cela est proportionnel à la vitesse"
-coef de peur: float "la peur est la distance maximal selon laquelle un blob moins fort s'approche d'un plus fort"
-coef force: float "la force permet de tuer un autre blob de force inferieur si le bolb a 2 nourriture et si il tue un autre blob il pert 1 de nourriture"
-coef vision-rayon: float "permet de connaitre le champs de vision du blob"
-coef vision-distance: float "permet de connaitre le champs de vision du blob"
----------------------------------- 
- fonction actualisation acceleration: "actualise l'acceleration"
-fonction actualisation direction: "actualise la direction du blob en tenant compte de si il voit de la nourriture,un mur, un ennemie et de son coef de peur sinon mouvement aléatoire "
- fonction moveTo: "cette fonction prend en compte la vitesse actuelle, la direction, l'acceleration, le coef vitesse, le coef masse, le coef d'energie, le coef d'instinct de survie" (la fonction est constament appelé pour faire bouger le blob)
-fonction manger: "supprime la nourriture sur la map et l'ajoute au blob"
-fonction tuer: "tuer un autre blob"
-fonction se reproduire: " crée un autre blob avec des coefs qui varient peu de celui d'avant" 
