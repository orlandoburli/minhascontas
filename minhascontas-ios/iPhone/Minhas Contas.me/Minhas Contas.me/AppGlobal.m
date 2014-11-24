//
//  AppGlobal.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "AppGlobal.h"
#import "TokenVo.h"

@implementation AppGlobal

@synthesize token;

#pragma mark Singleton Methods

+ (id)instance {
    static AppGlobal *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[self alloc] init];
    });
    return instance;
}

- (id)init {
    if (self = [super init]) {
        // ???
    }
    return self;
}

- (void)dealloc {
    // Should never be called, but just here for clarity really.
}


@end
