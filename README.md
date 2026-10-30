# ReduxEconomy

Sistema de economía para Minecraft, diseñado para ofrecer una API compartida que permita a otros mods interactuar con una misma economía.

## Acerca del proyecto

ReduxEconomy nace con la idea de ofrecer una solución económica centralizada, que pueda ser utilizada por diferentes mods sin necesidad de implementar sistemas de balances independientes.

Proporciona una API para consultar y modificar los balances de los jugadores, permitiendo que otros desarrolladores integren funcionalidades económicas en sus propios proyectos.

El objetivo es facilitar la compatibilidad entre mods y evitar la fragmentación de las economías dentro de un mismo servidor.

## Características

- Gestión de balances de jugadores.
- Operaciones para consultar, establecer, depositar y retirar dinero.
- API para integración con otros mods.
- Uso de `long` para representar los balances.
- Sistema económico compartido entre las integraciones compatibles.
- Diseñado para facilitar futuras extensiones.

## Integración

ReduxEconomy está pensado para funcionar como una dependencia de otros mods que necesiten implementar operaciones económicas.

A través de su API, los desarrolladores pueden acceder al servicio de economía y utilizar sus operaciones sin tener que gestionar directamente los datos monetarios.

### Proyectos compatibles

- **GTS-PORT:** integración del sistema económico de ReduxEconomy con el Global Trade Station, reemplazando su implementación económica original.

## Desarrollo

ReduxEconomy busca mantener una arquitectura sencilla y extensible, permitiendo que otros proyectos puedan utilizar su API sin depender de implementaciones económicas específicas.

La intención es seguir ampliando sus funcionalidades y compatibilidad con diferentes mods y plataformas.

## Créditos

Desarrollado por **Matías Álvarez (Kodari)**.

