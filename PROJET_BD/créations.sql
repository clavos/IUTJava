create type ob_entreprise_ty AS object (
numEntreprise varchar(30),
nomEntreprise varchar(30),
ville varchar(30),
département varchar(30));

create TABLE ob_entreprise of ob_entreprise_ty  (numEntreprise PRIMARY KEY);



create type ob_stage_ty AS object (
numStage varchar(30),
numEntreprise REF ob_entreprise_ty,
dateS DATE);

create TABLE ob_stage of ob_stage_ty (numStage PRIMARY KEY);


create type ob_etudiant_ty AS object (
numEtudiant varchar(30),
nomEtudiant varchar(30),
prenomEtudiant varchar(30),
numStage REF ob_stage_ty);

create TABLE ob_etudiant of ob_etudiant_ty  (numEtudiant PRIMARY KEY);


commit;



