abrir_conexion :-
	odbc_connect('swiprolog',_,
		     [user(root),
		     password(paco),
		     alias(swiprolog),
		     open(once)
		     ]).

cerrar_conexion :-
	odbc_disconnect('swiprolog').

fila(Cadena,X):-
	odbc_query(
	    'swiprolog',
	    Cadena,
	    row(X),
	    [types([default])
	]).
getDistinct(ColumnName,TableName,X):-
	%atom_concat("SELECT DISTINCT ",ColumnName,FS),
	%atom_concat(FS," FROM ",FSS),
	%atom_concat(FSS,TableName,FSSS),
	odbc_query(
	    'swiprolog',
	    'SELECT DISTINCT ~w FROM ~w'-[ColumnName,TableName],
	    row(X),
	    [types([default])
	]).

getCountBase(ColumnName,TableName,Valor,ColumnEstado,Estado,R):-
	%atom_concat("SELECT COUNT(",ColumnName,FS),
	%atom_concat(FS,") FROM ",FSS),
	%atom_concat(FSS,TableName,FSSS),
	%atom_concat(FSSS," WHERE ",FSSSS),
	%atom_concat(FSSSS,ColumnName,FSSSSS),
	%atom_concat(FSSSSS," = '",FSSSSSS),
	%atom_concat(FSSSSSS,Valor,FSSSSSSS),
	%atom_concat(FSSSSSSS,"' AND ",FSSSSSSSS),
	%atom_concat(FSSSSSSSS,ColumnEstado,FSSSSSSSSS),
	%atom_concat(FSSSSSSSSS," = '",FSSSSSSSSSS),
	%atom_concat(FSSSSSSSSSS,Estado,FSSSSSSSSSSS),
	%atom_concat(FSSSSSSSSSSS,"'",FSSSSSSSSSSSS),
	%writeln(FSSSSSSSSSSSS),
	findall(X,
		odbc_query(
	    'swiprolog',
	    'SELECT COUNT(~w) FROM ~w WHERE ~w = "~w" AND ~w = "~w"'-[ColumnName,TableName,ColumnName,Valor,ColumnEstado,Estado],
	    row(X),
	    [types([default])
	]),R).
	%%writeln(X).
	%%append(X,Y)

insertarEntropias(ColumnNamesString,TableName,ColumnBase,R):-
	atomic_list_concat(ColumnNames,',',ColumnNamesString),
	findall(A,getDistinct(ColumnBase,TableName,A),ValoresBase),
	forall(member(ColumnName,ColumnNames),
	       (
		   writeln(ValoresBase),
		   writeln(ColumnName),
		   findall(Q,getDistinct(ColumnName,TableName,Q),ValoresPosibles),
		   writeln(ValoresPosibles),
		   assertEntropia(ValoresPosibles,ColumnName,TableName,ColumnBase,ValoresBase,R)
	       )
	      ).

assertEntropia([],ColumnName,_,_,_,EntropiaAcumulada):-
	asserta(entropia(ColumnName,EntropiaAcumulada)).
assertEntropia([Valor|ValoresPosibles],ColumnName,TableName,ColumnBase,ValoresBase,EntropiaAcumulada):-
	asserta(enlaces(ColumnName,Valor)),
	element_at(Positivo,ValoresBase,1),
	element_at(Negativo,ValoresBase,2),
	getCountBase(ColumnName,TableName,Valor,ColumnBase,Positivo,RPos),
	getCountBase(ColumnName,TableName,Valor,ColumnBase,Negativo,RNeg),
	calculoEntropia(RPos,RNeg,EntropiaValorPosible),
	EntropiaNueva is EntropiaValorPosible + EntropiaAcumulada,
	assertEntropia(ValoresPosibles,ColumnName,TableName,ColumnBase,ValoresBase,EntropiaNueva).

calculoEntropia(NP,NN,Entropia):-
	OI is NP + NN,
	LIDIV is NP / OI,
	LDDIV is NN / OI,
	log2(LIDIV, RESLOGI),
	log2(LDDIV, RESLOGD),
	LI is (NP * -1) * RESLOGI,
	LD is (NN * -1) * RESLOGD,
	Entropia is (LI + LD),
	writeln(Entropia).

log2(0,Z):-
	Z is 0.
log2(X,Z):-
       Z is (log(X)/log(2)).

element_at(X, List, Pos) :-
    element_at(X, List, 1, Pos).
element_at(X, [X|_], Pos, Pos).
element_at(X, [_|T], Acc, Pos) :-
    (nonvar(Pos) -> Acc < Pos ; true),  % fail if Acc hits ground Pos
    Acc1 is Acc + 1,
    element_at(X, T, Acc1, Pos).
