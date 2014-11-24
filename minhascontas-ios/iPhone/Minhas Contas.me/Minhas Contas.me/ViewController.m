//
//  ViewController.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 15/10/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "ViewController.h"
#import "ViewCreateUserController.h"
#import "AcessoServices.h"
#import "AppGlobal.h"
#import "HomeViewController.h"
#import "DBManager.h"
#import "UsuarioBe.h"
#import "TokenBe.h"


@implementation ViewController

BOOL isSenhaMD5 = NO;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Cria o banco de dados
    [[DBManager getSharedInstance] createDB];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    // Aponta este controller para controlar o teclado.
    self.txtEmail.delegate = self;
    self.txtSenha.delegate = self;
    
    // TODO Testes
    
//    self.txtEmail.text = @"orlando.developermaster@gmail.com";
//    self.txtSenha.text = @"carol1408";
    
    // Load do Ultimo Token
    TokenBe *tokenBe = [[TokenBe alloc]init];
    TokenVo *token = [tokenBe getLastToken];
    
    if (token.usuario) {
        self.txtEmail.text = token.usuario.email;
        self.txtSenha.text = token.usuario.senha;
        isSenhaMD5 = YES;
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (BOOL)acceptsFirstResponder {
    return YES;
}

// Funcao para esconder o teclado ao tocar fora do input
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    // ???
}

-(void)dismissKeyboard {
    [self.txtEmail resignFirstResponder];
    [self.txtSenha resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    NSLog(@"Testando!!!");
    
    if (textField == self.txtEmail) {
        [textField resignFirstResponder];
        [self.txtSenha becomeFirstResponder];
    } else if (textField == self.txtSenha) {
        [self.txtSenha resignFirstResponder];
        
        // Clique do botao entrar
        [self entrar:self.btnEntrar];
    }
    return NO;
}

// Função de login
- (IBAction)entrar : (id) sender {
    
    AcessoServices *services = [[AcessoServices alloc] init];
    
    RetornoVo *retorno = [services login:self.txtEmail.text withSenha:self.txtSenha.text withSenhaMD5:isSenhaMD5];
    
    if (retorno.sucesso) {
        // Sucesso
        [self dismissKeyboard];
        [self showTimedAlert:retorno.mensagem withTitulo:@"Informação"];
        
        AppGlobal *app = [AppGlobal instance];
        
        app.token = retorno.token;
        
        // Salva os dados do Token e do Usuário
        UsuarioBe *usuarioBe = [[UsuarioBe alloc] init];
        [usuarioBe save:app.token.usuario];
        
        TokenBe *tokenBe = [[TokenBe alloc]init];
        [tokenBe save:app.token];
        
        // Tela Home
        HomeViewController *home = [self.storyboard instantiateViewControllerWithIdentifier:@"ViewHome"];
        
        [self.navigationController pushViewController:home animated:YES];
        
        [self presentViewController:home animated:YES completion:nil];

        
    } else {
        [self showTimedAlert:retorno.mensagem withTitulo:@"Aviso"];

        [self.txtEmail becomeFirstResponder];
    }
}

// Botao nao tenho cadastro
- (IBAction)naoTenhoCadastro:(id)sender {
    ViewCreateUserController *createUser = [self.storyboard instantiateViewControllerWithIdentifier:@"ViewCriarLogin"];
    
    [self.navigationController pushViewController:createUser animated:YES];
    
    [self presentViewController:createUser animated:YES completion:nil];
}

// Evento disparado ao alterar a senha
- (IBAction)editeiSenha:(id)sender {
    isSenhaMD5 = NO;
}

@end
