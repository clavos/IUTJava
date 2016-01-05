create type ob_entreprise_ty AS object (
numEntreprise varchar(10),
nomEntreprise varchar(10),
ville varchar(10),
département varchar(10));

create TABLE ob_entreprise of ob_entreprise_ty  (numEntreprise PRIMARY KEY);



create type ob_stage_ty AS object (
numStage varchar(10),
numEntreprise REF ob_entreprise_ty,
dateS DATE);

create TABLE ob_stage of ob_stage_ty (numStage PRIMARY KEY);


create type ob_etudiant_ty AS object (
numEtudiant varchar(10),
nomEtudiant varchar(10),
prenomEtudiant varchar(10),
numStage REF ob_stage_ty);

create TABLE ob_etudiant of ob_etudiant_ty  (numEtudiant PRIMARY KEY);




INSERT INTO ob_entreprise VALUES (ob_entreprise_ty('1','Thales','Velizy','Essonne'));
INSERT INTO ob_entreprise VALUES (ob_entreprise_ty('2','Microsoft','Issy','HautSeine'));

INSERT INTO ob_stage SELECT 1, Ref(ent), TO_DATE('01/02/2014','DD/MM/YYYY')FROM ob_entreprise ent WHERE numEntreprise =1;

INSERT INTO ob_etudiant VALUES(ob_etudiant_ty('1','Dupont','Pierre',null));

INSERT INTO ob_etudiant SELECT 2,'Jean','Bidule',Ref(stage) FROM ob_stage stage WHERE numStage=1;
commit;


select count (*) FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.dateS>=TO_DATE('01/01/2013', 'DD/MM/YYYY') and o.numStage.numEntreprise.nomEntreprise = 'Thales';

select count (*) FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.dateS>=TO_DATE('01/01/2014', 'DD/MM/YYYY') AND o.numStage.dateS<=TO_DATE('31/12/2014', 'DD/MM/YYYY');

