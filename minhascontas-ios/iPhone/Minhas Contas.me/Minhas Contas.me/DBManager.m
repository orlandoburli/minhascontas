//
//  DBManager.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "DBManager.h"
#import "sqlite3.h"
#import "AppDictionary.h"

@implementation DBManager

static DBManager *sharedInstance = nil;
static sqlite3 *database = nil;

+(DBManager*)getSharedInstance{
    if (!sharedInstance) {
        sharedInstance = [[super allocWithZone:NULL]init];
        [sharedInstance createDB];
    }
    return sharedInstance;
}

-(BOOL) openDB {
    BOOL isSuccess = NO;
    
    NSString *docsDir;
    NSArray *dirPaths;
    
    // Get the documents directory
    dirPaths = NSSearchPathForDirectoriesInDomains (NSDocumentDirectory, NSUserDomainMask, YES);
    docsDir = dirPaths[0];
    
    // Build the path to the database file
    databasePath = [[NSString alloc] initWithString:
                    [docsDir stringByAppendingPathComponent: @"minhascontas.db"]];
    
    const char *dbpath = [databasePath UTF8String];
    
    if (sqlite3_open(dbpath, &database) == SQLITE_OK) {
        isSuccess = YES;
    } else {
        NSLog(@"Falha ao abrir banco de dados!");
    }

    return isSuccess;
}

-(BOOL) closeDB {
    sqlite3_close(database);
    
    return YES;
}

-(BOOL) createDB {
    BOOL isSuccess = YES;

    if ([self openDB]) {
        // Cria a tabela de Tokens
        
        NSString *sqlCreateTableToken = [[NSString alloc] initWithFormat:@"CREATE TABLE IF NOT EXISTS %@ (%@ INTEGER PRIMARY KEY, %@ TEXT, %@ INTEGER, %@ DATE, %@ DATE, %@ TEXT)", tabelaToken, campoTokenIdToken, campoTokenIdUsuario, campoTokenChaveToken, campoTokenDataHoraCriacao, campoTokenDataUltimoAcesso, campoTokenExpirado];
        
        [self createTable:sqlCreateTableToken];
        
        // Cria a tabela de Usuários
        
        NSString *sqlCreateTableUsuario = [[NSString alloc] initWithFormat:@"CREATE TABLE IF NOT EXISTS %@ (%@ INTEGER PRIMARY KEY, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT, %@ TEXT)", tabelaUsuario, campoUsuarioIdUsuario, campoUsuarioNomeUsuario, campoUsuarioSobreNomeUsuario, campoUsuarioTipoAutenticacao, campoUsuarioEmail, campoUsuarioIdFacebook, campoUsuarioSenha, campoUsuarioFlagConfirmado, campoUsuarioPathFoto, campoUsuarioHashEmail];
        
        [self createTable:sqlCreateTableUsuario];
        
        // Cria a tabela de Categorias
        
        NSString *sqlCreateTableCategoria = [[NSString alloc] initWithFormat:@"CREATE TABLE IF NOT EXISTS %@ (%@ INTEGER PRIMARY KEY, %@ TEXT, %@ TEXT, %@ INTEGER, %@ TEXT, %@ TEXT)", tabelaCategoria, campoCategoriaIdCategoria, campoCategoriaNomeCategoria, campoCategoriaTipoCategoria, campoCategoriaIdCategoriaPai, campoCategoriaIdCategoriaReferencia, campoCategoriaSincronizado];
        
        [self createTable:sqlCreateTableCategoria];
        
        [self closeDB];
        return  isSuccess;
    } else {
        isSuccess = NO;
        NSLog(@"Failed to open/create database");
    }
    return isSuccess;
}

-(BOOL) createTable: (NSString *) sqlCreateTable {

    BOOL isSuccess = YES;

    char *errMsg;
    
    if (sqlite3_exec(database, [sqlCreateTable UTF8String], NULL, NULL, &errMsg)
        != SQLITE_OK)
    {
        isSuccess = NO;
        NSLog(@"Failed to create table: %@", sqlCreateTable);
    }
    
    return isSuccess;
}

-(BOOL) executeSql: (NSString *) sqlStatement {
    
    NSLog(@"Debug SQL Statement: %@", sqlStatement);
    
    sqlite3_stmt *statement = nil;

    if ([self openDB]) {
    
        const char *insert_stmt = [sqlStatement UTF8String];
    
        sqlite3_prepare_v2(database, insert_stmt,-1, &statement, NULL);
    
        if (sqlite3_step(statement) == SQLITE_DONE) {
            return YES;
        } else {
            NSLog(@"Erro ao executar SQL! %@", sqlStatement);
            return NO;
        }
    }
    
    sqlite3_reset(statement);
    
    return NO;
}

// Executa uma query e retorna um NSMultableArray como coleção de NSDictionary com chave / valor.
-(NSMutableArray *) executeQuery : (NSString *) sqlStatement {
    sqlite3_stmt *statement = nil;
    
    NSMutableArray *resultArray = [[NSMutableArray alloc] init];
    
    if (sqlite3_prepare_v2(database, [sqlStatement UTF8String], -1, &statement, NULL) == SQLITE_OK) {
        int columnCount = sqlite3_column_count(statement);

        while (sqlite3_step(statement) == SQLITE_ROW) {
            NSMutableDictionary *row = [[NSMutableDictionary alloc] init];
            
            for (int i = 0; i < columnCount; i++) {
                NSString *nome = [[NSString alloc] initWithFormat:@"%s", sqlite3_column_name(statement, i)];
                NSString *valor = [[NSString alloc] initWithFormat:@"%s", sqlite3_column_text(statement, i)];
                
                [row setObject:valor forKey:nome];
                
                [resultArray addObject:row];
            }
        }
        sqlite3_reset(statement);
    }

    return resultArray;
}


@end
