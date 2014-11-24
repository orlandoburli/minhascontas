//
//  DBManager.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DBManager : NSObject
{
    NSString *databasePath;
}

+(DBManager*)getSharedInstance;

-(BOOL) openDB;

-(BOOL) closeDB;

-(BOOL) createDB;

-(BOOL) executeSql: (NSString *) sqlStatement;

-(NSMutableArray *) executeQuery : (NSString *) sqlStatement;

@end
