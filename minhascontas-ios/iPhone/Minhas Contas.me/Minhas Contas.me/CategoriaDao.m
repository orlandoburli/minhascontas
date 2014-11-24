//
//  CategoriaDao.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 24/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "CategoriaDao.h"
#import "AppDictionary.h"
#import "DBManager.h"

@implementation CategoriaDao

-(void) insert: (CategoriaVo *) categoria {
    
    // Auto - incremento
    categoria.idCategoria = [self getMaxId:tabelaCategoria withMaxField:campoCategoriaIdCategoria];
    
    DBManager *manager = [DBManager getSharedInstance];
    
    [manager openDB];
    
    NSString *sqlInsertCategoria = [[NSString alloc] initWithFormat:@"INSERT INTO %@ (%@, %@, %@, %@, %@, %@) VALUES (\"%ld\", \"%@\", \"%@\", \"%ld\", \"%@\", \"%@\")", tabelaCategoria, campoCategoriaIdCategoria, campoCategoriaNomeCategoria, campoCategoriaTipoCategoria, campoCategoriaIdCategoriaPai, campoCategoriaIdCategoriaReferencia, campoCategoriaSincronizado, (long) categoria.idCategoria, categoria.nomeCategoria, categoria.tipoCategoria, categoria.idCategoriaPai, categoria.idCategoriaReferencia, categoria.sincronizado];
    
    [manager executeSql:sqlInsertCategoria];
    
    [manager closeDB];
}

-(void) update: (CategoriaVo *) categoria {
    // TODO Update
    
    DBManager *manager = [DBManager getSharedInstance];
    
    [manager openDB];

    NSString *sqlUpdateCategoria = [[NSString alloc] initWithFormat:@"UPDATE %@ SET %@ = \"%@\", %@ = \"%@\", %@ = \"%ld\", %@ = \"%@\", %@ = \"%@\" WHERE %@ = \"%ld\"", tabelaCategoria, campoCategoriaNomeCategoria, categoria.nomeCategoria, campoCategoriaTipoCategoria, categoria.tipoCategoria, campoCategoriaIdCategoriaPai, (long) categoria.idCategoriaPai, campoCategoriaIdCategoriaReferencia, categoria.idCategoriaReferencia, campoCategoriaSincronizado, categoria.sincronizado, campoCategoriaIdCategoria, (long) categoria.idCategoria];
    
    [manager executeSql:sqlUpdateCategoria];
    
    [manager closeDB];
}

-(void) delete : (CategoriaVo *) categoria {
    /// TODO Delete
}

-(CategoriaVo *) get: (NSInteger ) idCategoria {
    
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectCategoria = [[NSString alloc] initWithFormat:@"%@ WHERE %@ = \"%ld\"", [self getSqlBase], campoCategoriaIdCategoria, (long) idCategoria];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectCategoria];
    
    [[DBManager getSharedInstance] closeDB];
    
    if (dados.count > 0) {
        NSDictionary *row = dados[0];
        
        CategoriaVo *categoria = [[CategoriaVo alloc] init];
        
        [self fillCategoria:categoria withNSDictionary:row ];
        
        return categoria;
    }
    
    return nil;
}

-(NSArray *) getListPai {

    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectCategoria = [[NSString alloc] initWithFormat:@"%@ ORDER BY %@", [self getSqlBase], campoCategoriaNomeCategoria];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectCategoria];
    
    NSMutableArray *retorno = [[NSMutableArray alloc] initWithCapacity:dados.count];
    
    for (int i = 0; i < dados.count; i++) {
        NSDictionary *row = dados[0];
        
        CategoriaVo *categoria = [[CategoriaVo alloc] init];
        
        [self fillCategoria:categoria withNSDictionary:row];
        
        retorno[i] = categoria;
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    return retorno;
}

-(NSString *) getSqlBase {
    NSString *sqlSelectCategoriaBase = [[NSString alloc] initWithFormat:@"SELECT %@, %@, %@, %@, %@, %@ FROM %@ ", campoCategoriaIdCategoria, campoCategoriaNomeCategoria, campoCategoriaTipoCategoria, campoCategoriaIdCategoriaPai, campoCategoriaIdCategoriaReferencia, campoCategoriaSincronizado, tabelaCategoria];
    
    return sqlSelectCategoriaBase;
}

-(void) fillCategoria: (CategoriaVo *) categoria withNSDictionary: (NSDictionary *) dictionary {
//    token.idToken = [[dictionary valueForKey:campoTokenIdToken] integerValue];
//    token.chaveToken = [dictionary valueForKey:campoTokenChaveToken];
//    token.idUsuario = [[dictionary valueForKey:campoTokenIdUsuario] integerValue];
//    
//    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
//    [formatter setDateFormat:@"dd/MM/yyyy hh24:mm:ss"];
//    
//    token.dataHoraCriacao = [formatter dateFromString:[dictionary valueForKey:@"dataHoraCriacao"]];
//    token.dataUltimoAcesso = [formatter dateFromString:[dictionary valueForKey:@"dataUltimoAcesso"]];
//    
//    token.expirado = [dictionary valueForKey:campoTokenExpirado];
}



@end
