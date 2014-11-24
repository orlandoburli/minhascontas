//
//  BaseViewController.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseViewController.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}


-(void)dismissAlert:(UIAlertView *) alertView
{
    [alertView dismissWithClickedButtonIndex:0 animated:YES];
}

-(void)timedAlert: (UIAlertView *) alert
{
    [self performSelector:@selector(dismissAlert:) withObject:alert afterDelay:5];
}

-(void) showTimedAlert: (NSString *)mensagem withTitulo: (NSString *) titulo {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:titulo message:mensagem delegate:self cancelButtonTitle:@"Ok" otherButtonTitles:nil];
    
    [alert show];
    
    [self timedAlert: alert];
}


@end
