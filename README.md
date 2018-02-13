# Projet IA - le monde du Wumpus
	
          # Présentation	
	
Le	monde	de	Wumpus	est	une	caverne	composée	de	salles	reliées	par	des	corridors.	Dans	cette	caverne,	se	cache	le	terrible	Wumpus,	monstre	qui	dévore	quiconque	entre	dans	son	repaire,	et	également	un	trésor.	Le	but	de	votre	intelligence	artificielle,	ou	agent,	est	de	trouver	un	chemin	dans	une	caverne	pour	récupérer	l’or	sans	mourir.	

          # La caverne	et l’agent	
	
Une	caverne	est	générée	aléatoirement	comme	une	matrice	dont	le	nombre	de	lignes	et	le	nombres	de	colonnes	doivent	être	au	minimum	de	quatre.	Chaque	case	peut	:	
• Etre	vide	;	
• Contenir	un	puit	sans	fond	;	
• Contenir	le	Wumpus	;	
• Contenir	l’or	!		

Si	votre	agent	est	sur	une	case	avec	un	puit	sans	fond,	il	meurt	et	la	partie	s’arrête.	Si	votre	agent	est	sur	une	case	avec le	Wumpus,	il	meurt	en	étant	mangé	par	le	Wunpus	et	la	partie	s’arrête.	Si	votre	agent	tombe	sur	la	case	avec	l’or	et	qu’il	le	saisit,	vous	gagnez	la	partie.	

L’agent	peut	avancer	tout	droit,	tourner	à	gauche	ou	tourner	à	droite.	Il	peut	également	tirer	une	flèche	tout	droit	dans	la	direction	dans	laquelle	il	se	trouve.		
	
L’agent	commence	toujours	dans	la	case	en	bas	à	gauche.		
	
L’agent	possède	également	cinq	capteurs	:	
• Les	cases	directement	adjacentes	(pas	en	diagonales)	au	Wumpus	ainsi	que	la	case	du	Wumpus	diffusent	une	odeur	;	
• Les	cases	adjacentes	à	un	puit	(pas	en	diagonales)	diffusent	une	brise	;	
• Dans	la	case	où	se	trouve	l’or,	l’agent	perçoit	une	lueur	;	
• Lorsque	l'agent	percute	un	mur,	il	reçoit	un	choc	;	
• Quand	le	Wumpus	est	tué,	un	cri	épouvantable	est	entendu	dans	toute	la	caverne.	
	
          # Travail	demandé	
	
Vous	pouvez	utiliser	n’importe	quel	langage	de	programmation,	sauf	ceux	dédiés	au	Web	(JEE,	PhP,	Javascript,	Ruby,	ASP.NET,	…).		
	
Chaque	groupe	est	constitué	d’au	moins	cinq	personnes	pour	faire	les	tâches	suivantes	:	
• Modéliser	la	caverne	;	
• Créer	un	générateur	de	cavernes	où	l’utilisateur	indiquera	le	nombre	de	lignes	et	de	colonnes	de	la	caverne	et	où	les	cases	seront	remplies	aléatoirement	;	
• Modéliser	votre	agent	;	
• Implémenter	votre	agent	;		
• A	la	fin	de	chaque	partie,	vous	devez	être	capable	d’analyser	le	chemin	pris	votre	agent	et	de	le	comparer	au	chemin	optimal	pour	la	caverne	de	la	partie	;	
• Ecrire	un	rapport	présentant	:	
  o Le	choix	des	technologies	;	
  o La	modélisation	de	la	caverne,	; 
  o La	génération	aléatoire	des	cavernes	;	
  o Le	comportement	de	votre	agent	;	
  o Les	performances	de	votre	agent.	
• Faire	une	présentation	orale	en	équipe	qui	inclut	:	
  o Une	démonstration	de	votre	application	;	
  o L’explication	de	votre	vision	des	cavernes	;	
  o Le	comportement	de	votre	agent	et	ses	performances	;	
  o Votre	organisation	;	
  o Votre	retour	sur	le	projet.	
	Vous	êtes	libres	d’utiliser	n’importe	quelle	méthode	liée	à	l’intelligence	artificielle.	
	
Attention,	tout	plagiat	et	tout	travail	fait	sans	équipe,	sera	sanctionné	par	un	zéro	pour	toute	l’équipe.	
